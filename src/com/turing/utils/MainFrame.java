package com.turing.utils;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame
{
  private JPanel contentPane;
  private JTextArea tarIn;
  private JTextArea tarOut;

  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          MainFrame frame = new MainFrame();
          frame.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }

  public MainFrame()
  {
    setTitle("Servlet转换器");
    setDefaultCloseOperation(2);
    setBounds(100, 100, 450, 300);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(this.contentPane);

    JToolBar toolBar = new JToolBar();
    this.contentPane.add(toolBar, "North");

    JButton btnKaiShi = new JButton("开始转换");
    btnKaiShi.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        String[] ss = MainFrame.this.tarIn.getText().split("\n");
        for (int i = 0; i < ss.length; ++i)
        {
          StringBuffer sb = new StringBuffer();
          for (int j = 0; j < ss[i].length(); ++j)
          {
            char c = ss[i].charAt(j);
            if (c == '"')
            {
              sb.append("\\\"");
            }
            else
            {
              sb.append(c);
            }
          }

          ss[i] = sb.toString();

          MainFrame.this.tarOut.append("out.println(\"");
          MainFrame.this.tarOut.append(ss[i]);
          MainFrame.this.tarOut.append("\");");
          MainFrame.this.tarOut.append("\n");
        }
      }

    });
    toolBar.add(btnKaiShi);

    JButton btnTuiChu = new JButton("退出");
    btnTuiChu.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        MainFrame.this.dispose();
      }

    });
    JButton btnShuaXin = new JButton("刷新");
    btnShuaXin.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        MainFrame.this.tarIn.setText("");
        MainFrame.this.tarOut.setText("");
      }

    });
    toolBar.add(btnShuaXin);
    toolBar.add(btnTuiChu);

    JSplitPane splitPane = new JSplitPane();
    splitPane.setResizeWeight(0.5D);
    this.contentPane.add(splitPane, "Center");

    JScrollPane scrollPane = new JScrollPane();
    splitPane.setLeftComponent(scrollPane);

    this.tarIn = new JTextArea();
    scrollPane.setViewportView(this.tarIn);

    JScrollPane scrollPane_1 = new JScrollPane();
    splitPane.setRightComponent(scrollPane_1);

    this.tarOut = new JTextArea();
    this.tarOut.setEditable(false);
    scrollPane_1.setViewportView(this.tarOut);
  }
}