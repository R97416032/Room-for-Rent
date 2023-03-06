
// SensorDlg.h: 头文件
//

#pragma once


// CSensorDlg 对话框
class CSensorDlg : public CDialogEx
{
// 构造
public:
	CSensorDlg(CWnd* pParent = nullptr);	// 标准构造函数

// 对话框数据
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_SENSOR_DIALOG };
#endif

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV 支持


// 实现
protected:
	HICON m_hIcon;
	// 生成的消息映射函数
	virtual BOOL OnInitDialog();
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	DECLARE_MESSAGE_MAP()
public:
	CString ad;

	//线程
	CWinThread *my;
	afx_msg void OnBnClickedButtonSend();
	int po;
	int ro;
	BOOL te;
	BOOL hu;
	BOOL m_pm;
	BOOL m_noise;
	int m_time;
	CButton StopSend;
	afx_msg void OnBnClickedButtonStop();
};
