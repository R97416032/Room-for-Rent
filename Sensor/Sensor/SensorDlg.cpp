
// SensorDlg.cpp: 实现文件
//

#include "pch.h"
#include "framework.h"
#include "Sensor.h"
#include "SensorDlg.h"
#include "afxdialogex.h"
#include "Thread.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CSensorDlg 对话框



CSensorDlg::CSensorDlg(CWnd* pParent /*=nullptr*/)
	: CDialogEx(IDD_SENSOR_DIALOG, pParent)
	, ad(_T(""))
	, po(0)
	, ro(0)
	, te(FALSE)
	, hu(FALSE)
	, m_pm(FALSE)
	, m_noise(FALSE)
	, m_time(0)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CSensorDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, address, ad);
	DDX_Text(pDX, port, po);
	DDX_Text(pDX, room, ro);
	DDX_Check(pDX, temperature, te);
	DDX_Check(pDX, humidity, hu);
	DDX_Check(pDX, pm, m_pm);
	DDX_Check(pDX, noise, m_noise);
	DDX_Text(pDX, time, m_time);
	DDX_Control(pDX, IDC_BUTTON_STOP, StopSend);
}

BEGIN_MESSAGE_MAP(CSensorDlg, CDialogEx)
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(ID_BUTTON_SEND, &CSensorDlg::OnBnClickedButtonSend)
	ON_BN_CLICKED(IDC_BUTTON_STOP, &CSensorDlg::OnBnClickedButtonStop)
END_MESSAGE_MAP()


// CSensorDlg 消息处理程序

BOOL CSensorDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// 设置此对话框的图标。  当应用程序主窗口不是对话框时，框架将自动
	//  执行此操作
	SetIcon(m_hIcon, TRUE);			// 设置大图标
	SetIcon(m_hIcon, FALSE);		// 设置小图标

	// TODO: 在此添加额外的初始化代码
	
	return TRUE;  // 除非将焦点设置到控件，否则返回 TRUE
}

// 如果向对话框添加最小化按钮，则需要下面的代码
//  来绘制该图标。  对于使用文档/视图模型的 MFC 应用程序，
//  这将由框架自动完成。

void CSensorDlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // 用于绘制的设备上下文

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// 使图标在工作区矩形中居中
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// 绘制图标
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialogEx::OnPaint();
	}
}

//当用户拖动最小化窗口时系统调用此函数取得光标
//显示。
HCURSOR CSensorDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}



void CSensorDlg::OnBnClickedButtonSend()
{
	// TODO: 在此添加控件通知处理程序代码
	 my=AfxBeginThread(ThreadWork, (LPVOID)this);
}


void CSensorDlg::OnBnClickedButtonStop()
{
	// TODO: 在此添加控件通知处理程序代码
	my->SuspendThread();
}
