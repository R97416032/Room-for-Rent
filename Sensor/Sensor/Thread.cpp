#include "pch.h"
#include "Thread.h"
#include "SensorDlg.h"

void lsend(SOCKET sock, char * sd, int len)
{
	int count = 0;
	char* lsd = sd;
	while (count < len) {
		count = send(sock, lsd, len, 0);
		lsd += count;
		len -= count;
	}
}

void func(CString address, int port, int Room, BOOL t, BOOL h, BOOL p, BOOL n,int mtime)
{
	SYSTEMTIME st;
	CString strDate, strTime;
	GetLocalTime(&st);
	strDate.Format(L"%4d-%2d-%2d", st.wYear, st.wMonth, st.wDay); 
	srand((unsigned)time(NULL));
	WSADATA ver;
	//����SOCKET��
	WSAStartup(MAKEWORD(1, 1), &ver);
	//���������׽���
	SOCKET sock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);


	HWND hWnd = AfxGetMainWnd()->m_hWnd;
	
	SOCKADDR_IN addr;
	memset(&addr, 0, sizeof(SOCKADDR_IN));
	//��ʼ����ַ
	addr.sin_family = AF_INET;
	addr.sin_port = htons(port);


	char a[11];
	const char *cPort = _itoa(port, a, 10);
	
	char str[100];
	wsprintfA(str, "%ls", address);
	addr.sin_addr.S_un.S_addr = inet_addr(str);


	//����������ַ
	int con = connect(sock, (SOCKADDR *)& addr, sizeof(SOCKADDR_IN));
	if (con<0) {
		MessageBox(hWnd, L"����ʧ��!", L"��ʾ", MB_OK);
		printf("sock error!");
		return;
	}
	else {
		MessageBox(hWnd, L"���ӳɹ�!", L"��ʾ", MB_OK);
	}
	char sd[1000];
	char dt[200];
	char rd[1000];
	int room = Room;
	
	double temperature = -99;
	double humidity = -99;
	while(TRUE){
		memset(dt, 0, 200);
		memset(sd, 0, 1000);
		memset(rd, 0, 1000);

		int pm = -99;
		if (p)
			pm = (rand() % 100);
		int noise = -99;
		if (n)
			noise = (rand() % 80);
		//�õ��¶�
		if (t) {
			if(st.wMonth>=10)
				temperature = (rand() % (21)-12);
			else if(st.wMonth >= 5)
				temperature = (rand() % (21) + 12);
			else
				temperature = (rand() % (21));
		}
		//�õ�ʪ��ֵ
		if (h)
			humidity = (rand() * 1.0 / RAND_MAX);
		/*
		POST /setSensor HTTP/1.1
		Host:127.0.0.1:8080
		Content-Type: application/json
		Connection: keep-alive
		Content-Length: 55
		{"room":1,"temperature":35.6,"humidity":0.09,"pm":96,"noise":40}
		*/
		//���ݶ�
		sprintf(dt, "{\"room\":%d,\"temperature\":%.1f,\"humidity\":%.2f,\"pm\":%d,\"noise\":%d}", room, temperature, humidity, pm, noise);
		//���ݶγ���
		int dtlen = strlen(dt);
		//ƴ�ӷ��͵���Ϣ
		strcpy(sd, "POST /sensor/setSensor HTTP/1.1\r\nHost:");
		strcat(sd, str);
		strcat(sd, ":");
		strcat(sd, cPort);
		strcat(sd, "\r\nContent-Type: application/json\r\nConnection: keep-alive\r\nContent-Length: ");
		int hlen = strlen(sd);
		char* sdlen = sd + hlen;
		sprintf(sdlen, "%d\r\n\r\n", dtlen);
		hlen = strlen(sd);
		sdlen = sd + hlen;
		strcpy(sdlen, dt);
		lsend(sock, sd, strlen(sd));
		recv(sock, rd, 1000, 0);
		//���״̬��  ��10��11��12λ����Ϊ2XXʱ���ӳɹ������Լ���10λ�Ϳ�����
		if (rd[9]!='2') {
			MessageBox(hWnd, L"�ϴ�ʧ�ܣ�", L"��ʾ", MB_OK);
			return;
		}
		Sleep(mtime*1000);
	}
}



UINT ThreadWork(LPVOID pParam)
{
	CWnd* hd = AfxGetApp()->GetMainWnd();
	//��ȡ��ַ��Ϣ
	CString address;
	hd->GetDlgItem(1002)->GetWindowTextW(address);
	//ͨ��id��intֵ��ȡ�ؼ�
	int port = hd->GetDlgItemInt(1003, NULL, 1);
	int Room = hd->GetDlgItemInt(1004, NULL, 1);
	int time = hd->GetDlgItemInt(1009, NULL, 1);
	BOOL t;
	CButton* pBtn = (CButton*)hd->GetDlgItem(1005);
	t = pBtn->GetCheck();
	BOOL h;
	pBtn = (CButton*)hd->GetDlgItem(1006);
	h = pBtn->GetCheck();
	BOOL p;
	pBtn = (CButton*)hd->GetDlgItem(1007);
	p = pBtn->GetCheck();
	BOOL n;
	pBtn = (CButton*)hd->GetDlgItem(1008);
	n = pBtn->GetCheck();
	func(address, port, Room, t, h, p, n,time);
	return 0;
}
