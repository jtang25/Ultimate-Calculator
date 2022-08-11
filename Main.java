/*
name: Jason Tang
date: 2022.08.10
program name: JASON'S ULTIMATE CALC
*/

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.script.*;
import org.math.plot.*;

class Main {
  // public variables
  public static int bc = 0;
  public static boolean raddeg = true;
  public static boolean inverse = false;
  public static String equation = "";
  public static String displayeq = "";
  public static String number = "";
  public static JFrame frame;
  public static JTextField num;
  public static int m = 1;
  public static int b = -50;
  public static JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, bf1, bf2, bf3, bf4, bf5, bf6, bf7, bf8, bf9, bf10, bf11, bf12, bf13, bf14, bf15, bf16;
  public static JButton p1, p2, f2, f3, f4, f5, f6, funct;
  public static String preveq = "";
  public static JFrame pi;
  public static int numThrows = 0;
  public static ImageIcon image = new ImageIcon(new ImageIcon("pythag1.png").getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH));
  public static JLabel picLabel_h = new JLabel(image);
  public static JLabel picLabel_s = new JLabel(image);

  public static JFrame e;
  //pythag variables
  public static double pyth_a = 0.0;
  public static double pyth_b = 0.0;
  public static double pyth_c = 0.0;

  //linear graph variables
  public static double graph_m = 0.0;
  public static double graph_b = 0.0;

  //exponential graph variables
  public static double exp_a = 0.0;
  public static double exp_b = 0.0;
  public static double exp_k = 0.0;

  //sin/cos/tan graph variables
  public static double sct_a = 0.0;
  public static double sct_k = 0.0;
  public static double sct_b = 0.0;
  public static double sct_c = 0.0;

  //parabolic graph variables
  public static double pab_a = 0.0;
  public static double pab_k = 0.0;
  public static double pab_h = 0.0;

   //logarithmic graph variables
  public static double log_a = 0.0;
  public static double log_b = 0.0;
  public static double log_k = 0.0;
  public static double log_h = 0.0;
  

  public static void main(String[] args) {

    double[] x = {0.0,1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0};
    double[] y = new double[x.length];
    for(int z = 0; z < x.length; z++){
      y[z] = Math.sin(0.5*x[z]);
    }

    //graphing calculator display and calculations
    JFrame graphing = new JFrame("Graphing Calculator");
    graphing.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        frame.show();
        e.getWindow().dispose();
      }
    });
    graphing.setSize(500,300);
    graphing.setLayout(new BorderLayout());
    Panel centerPane = new Panel(new GridBagLayout());
    JLabel func_type = new JLabel("Function Type:");
    String fcombobox[]={"Linear","Exponential","Sine","Cosine","Tangent","Parabolic"};    
    func_type.setMinimumSize(new Dimension(250,20));
    func_type.setPreferredSize(new Dimension(250,20));
    func_type.setMaximumSize(new Dimension(250,20));
    JComboBox cb=new JComboBox(fcombobox);    
    cb.setMinimumSize(new Dimension(250,20));
    cb.setPreferredSize(new Dimension(250,20));
    cb.setMaximumSize(new Dimension(250,20));
    cb.setBounds(50,50,90,20);
    centerPane.add(func_type);
    centerPane.add(cb);
    graphing.add(centerPane, BorderLayout.CENTER);
    JPanel graph_bottom = new JPanel();
    graph_bottom.setLayout(new GridLayout());
    
    //graph
    JPanel graph_panel = new JPanel();
    graph_panel.setLayout(new BorderLayout());
    JLabel graph_var = new JLabel(" = ");
    JTextField graph_textBox = new JTextField(10);
    graph_textBox.setSize(40, 10);
    JPanel graph_panelMiddle = new JPanel();
    JLabel graph_formula = new JLabel("y = mx + b");
    graph_panelMiddle.add(graph_var);
    graph_formula.setHorizontalAlignment(SwingConstants.CENTER);
    graph_panelMiddle.add(graph_textBox);
    graph_panel.add(graph_formula, BorderLayout.CENTER);
    graph_panel.add(graph_panelMiddle, BorderLayout.NORTH);

    //submit button that also calculates the x and y values
    JButton sub = new JButton("Submit");
    sub.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try{
          func_type.setSize(50,20);
          cb.setSize(50,20);
          graphing.remove(centerPane);
          centerPane.hide();
          graphing.add(graph_panel, BorderLayout.NORTH);
          graphing.setSize(500,299);
          graphing.setSize(500,300);
          //linear graph
          if(cb.getSelectedItem()=="Linear"){
            if(graph_var.getText()==" = "){
              graph_var.setText("m = ");
              graph_formula.setText("y = mx + b");
            }
            else if(graph_var.getText()=="m = "){
              graph_m = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = ("+graph_m+")x + b");
              graph_textBox.setText("");
              graph_var.setText("b = ");
            }
            else if(graph_var.getText()=="b = " ){
              graph_b = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = ("+graph_m+")x + "+graph_b);
              graph_textBox.setText("");
              graph_var.setText("m = ");
              graphing.hide();
              double[] x = new double[50];
              double[] y = new double[50];
              System.out.println(graph_m);
              for(int z = 0; z < x.length; z++){
                x[z] = (-x.length/2)+z;
                y[z] = graph_m*x[z]+graph_b;
                System.out.print(graph_m+" ");
                System.out.println(y[z]);
              }
              //plot linear graph
              Plot2DPanel plot = new Plot2DPanel();
              plot.addLinePlot("my plot", x, y);
              JFrame frame1 = new JFrame("a plot panel");
              frame1.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                  frame.show();
                  e.getWindow().dispose();
                }
              });
              frame1.setSize(500,300);
              frame1.setContentPane(plot);
              frame1.setVisible(true);
              graphing.hide();
              graph_m = 0.0;
              graph_b = 0.0;
              graph_var.setText(" = ");
              graph_textBox.setText("");
              graph_formula.setText("y = mx + b");
              graphing.remove(graph_panel);
              graphing.add(centerPane);
              frame1.show();
            }
          }
          //exponential graph
          else if(cb.getSelectedItem()=="Exponential"){
            if(graph_var.getText()==" = "){
              graph_var.setText("a = ");
              graph_formula.setText("y = a^(x + k) + b");
            }
            else if(graph_var.getText()=="a = "){
              exp_a = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+exp_a+"^(x + k) + b");
              graph_textBox.setText("");
              graph_var.setText("k = ");
            }
            else if(graph_var.getText()=="k = " ){
              exp_k = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+exp_a+"^(x + "+exp_k+") + b");
              graph_textBox.setText("");
              graph_var.setText("b = ");
            }
            else if(graph_var.getText()=="b = "){
              exp_b = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+exp_a+"^(x + "+exp_k+") + "+exp_b);
              graph_textBox.setText("");
              graph_var.setText("a = ");
              graphing.hide();
              double[] x = new double[15];
              double[] y = new double[15];
              System.out.println(exp_a);
              System.out.println(exp_k);
              System.out.println(exp_b);
              for(int z = 0; z < x.length; z++){
                x[z] = (-x.length/2)+z;
                if(exp_a<0){
                  y[z] = -(Math.pow(Math.abs(exp_a), (x[z] + exp_k)))+exp_b;
                  System.out.println("negative");
                }
                else{
                  y[z] = Math.pow(Math.abs(exp_a), (x[z] + exp_k))+exp_b;
                }
                System.out.println(y[z]);
              }
              //plot exponential graph
              Plot2DPanel plot = new Plot2DPanel();
              plot.addLinePlot("my plot", x, y);
              JFrame frame1 = new JFrame("a plot panel");
              frame1.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                  frame.show();
                  e.getWindow().dispose();
                }
              });
              frame1.setSize(500,300);
              frame1.setContentPane(plot);
              frame1.setVisible(true);
              graphing.hide();
              graph_m = 0.0;
              graph_b = 0.0;
              graph_var.setText(" = ");
              graph_textBox.setText("");
              graph_formula.setText("y = mx + b");
              graphing.remove(graph_panel);
              graphing.add(centerPane);
              frame1.show();
            }
          }
          //sin graph
          else if(cb.getSelectedItem()=="Sine"){
            if(graph_var.getText()==" = "){
              graph_var.setText("a = ");
              graph_formula.setText("y = a*sin(k(x - b) + c");
            }
            else if(graph_var.getText()=="a = "){
              sct_a = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+sct_a+"*sin(k(x - b) + c");
              graph_textBox.setText("");
              graph_var.setText("k = ");
            }
            else if(graph_var.getText()=="k = " ){
              sct_k = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+sct_a+"*sin("+sct_k+"(x - b) + c");
              graph_textBox.setText("");
              graph_var.setText("b = ");
            }
            else if(graph_var.getText()=="b = "){
              sct_b = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+sct_a+"*sin("+sct_k+"(x - "+sct_b+") + c");
              graph_textBox.setText("");
              graph_var.setText("c = ");
            }
            else if(graph_var.getText()=="c = " ){
              sct_c = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+sct_a+"*sin("+sct_k+"(x - "+sct_b+") + "+sct_c);
              graph_textBox.setText("");
              graph_var.setText("a = ");
              graphing.hide();
              double[] x = new double[1441];
              double[] y = new double[1441];
              for(int z = 0; z < x.length; z++){
                x[z] = (-x.length/2)+z;
                y[z] = sct_a*Math.sin(Math.toRadians(sct_k*x[z]-sct_b))+sct_c;
                System.out.println(y[z]);
              }
              //plot sin graph
              Plot2DPanel plot = new Plot2DPanel();
              plot.addLinePlot("my plot", x, y);
              JFrame frame1 = new JFrame("a plot panel");
              frame1.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                  frame.show();
                  e.getWindow().dispose();
                }
              });
              frame1.setSize(500,300);
              frame1.setContentPane(plot);
              frame1.setVisible(true);
              graphing.hide();
              graph_m = 0.0;
              graph_b = 0.0;
              graph_var.setText(" = ");
              graph_textBox.setText("");
              graph_formula.setText("y = mx + b");
              graphing.remove(graph_panel);
              graphing.add(centerPane);
              frame1.show();
            }
          }
          //cosine graph
          else if(cb.getSelectedItem()=="Cosine"){
            if(graph_var.getText()==" = "){
              graph_var.setText("a = ");
              graph_formula.setText("y = a*cos(k(x - b) + c");
            }
            else if(graph_var.getText()=="a = "){
              sct_a = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+sct_a+"*cos(k(x - b) + c");
              graph_textBox.setText("");
              graph_var.setText("k = ");
            }
            else if(graph_var.getText()=="k = " ){
              sct_k = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+sct_a+"*cos("+sct_k+"(x - b) + c");
              graph_textBox.setText("");
              graph_var.setText("b = ");
            }
            else if(graph_var.getText()=="b = "){
              sct_b = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+sct_a+"*cos("+sct_k+"(x - "+sct_b+") + c");
              graph_textBox.setText("");
              graph_var.setText("c = ");
            }
            else if(graph_var.getText()=="c = " ){
              sct_c = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+sct_a+"*cos("+sct_k+"(x - "+sct_b+") + "+sct_c);
              graph_textBox.setText("");
              graph_var.setText("a = ");
              graphing.hide();
              double[] x = new double[1441];
              double[] y = new double[1441];
              for(int z = 0; z < x.length; z++){
                x[z] = (-x.length/2)+z;
                y[z] = sct_a*Math.cos(Math.toRadians(sct_k*x[z]-sct_b))+sct_c;
                System.out.println(y[z]);
              }
              //plot cosine graph
              Plot2DPanel plot = new Plot2DPanel();
              plot.addLinePlot("my plot", x, y);
              JFrame frame1 = new JFrame("a plot panel");
              frame1.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                  frame.show();
                  e.getWindow().dispose();
                }
              });
              frame1.setSize(500,300);
              frame1.setContentPane(plot);
              frame1.setVisible(true);
              graphing.hide();
              graph_m = 0.0;
              graph_b = 0.0;
              graph_var.setText(" = ");
              graph_textBox.setText("");
              graph_formula.setText("y = mx + b");
              graphing.remove(graph_panel);
              graphing.add(centerPane);
              frame1.show();
            }
          }
          //tangent graph
          else if(cb.getSelectedItem()=="Tangent"){
            if(graph_var.getText()==" = "){
              graph_var.setText("a = ");
              graph_formula.setText("y = a*tan(k(x - b) + c");
            }
            else if(graph_var.getText()=="a = "){
              sct_a = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+sct_a+"*tan(k(x - b) + c");
              graph_textBox.setText("");
              graph_var.setText("k = ");
            }
            else if(graph_var.getText()=="k = " ){
              sct_k = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+sct_a+"*tan("+sct_k+"(x - b) + c");
              graph_textBox.setText("");
              graph_var.setText("b = ");
            }
            else if(graph_var.getText()=="b = "){
              sct_b = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+sct_a+"*tan("+sct_k+"(x - "+sct_b+") + c");
              graph_textBox.setText("");
              graph_var.setText("c = ");
            }
            else if(graph_var.getText()=="c = " ){
              sct_c = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+sct_a+"*tan("+sct_k+"(x - "+sct_b+") + "+sct_c);
              graph_textBox.setText("");
              graph_var.setText("a = ");
              graphing.hide();
              double[] x = new double[1441];
              double[] y = new double[1441];
              for(int z = 0; z < x.length; z++){
                x[z] = (-x.length/2)+z;
                y[z] = sct_a*Math.tan(Math.toRadians(sct_k*x[z]-sct_b))+sct_c;
                if(y[z]>1.0){
                  y[z] = 1.0;
                }
                else if(y[z]<1.0){
                  y[z] = -1.0;
                }
                System.out.println(y[z]);
              }
              //plot tangent graph
              Plot2DPanel plot = new Plot2DPanel();
              plot.addLinePlot("my plot", x, y);
              JFrame frame1 = new JFrame("a plot panel");
              frame1.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                  frame.show();
                  e.getWindow().dispose();
                }
              });
              frame1.setSize(500,300);
              frame1.setContentPane(plot);
              frame1.setVisible(true);
              graphing.hide();
              graph_m = 0.0;
              graph_b = 0.0;
              graph_var.setText(" = ");
              graph_textBox.setText("");
              graph_formula.setText("y = mx + b");
              graphing.remove(graph_panel);
              graphing.add(centerPane);
              frame1.show();
            }
          }
          //parabolic graph
          else if(cb.getSelectedItem()=="Parabolic"){
            if(graph_var.getText()==" = "){
              graph_var.setText("a = ");
              graph_formula.setText("y = a(x-h)^2+k");
            }
            else if(graph_var.getText()=="a = "){
              pab_a = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+pab_a+"(x-h)^2+k");
              graph_textBox.setText("");
              graph_var.setText("h = ");
            }
            else if(graph_var.getText()=="h = " ){
              pab_h = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+pab_a+"(x-"+pab_h+")^2+k");
              graph_textBox.setText("");
              graph_var.setText("k = ");
            }
            else if(graph_var.getText()=="k = " ){
              pab_k = Double.parseDouble(graph_textBox.getText());
              graph_formula.setText("y = "+pab_a+"(x-"+pab_h+")^2+"+pab_k);
              graph_textBox.setText("");
              graph_var.setText("a = ");
              graphing.hide();
              double[] x = new double[1441];
              double[] y = new double[1441];
              for(int z = 0; z < x.length; z++){
                x[z] = (-x.length/2)+z;
                y[z] = pab_a*Math.pow(x[z]-pab_h,2)+pab_k;
                System.out.println(y[z]);
              }
              //plot parabolic graph
              Plot2DPanel plot = new Plot2DPanel();
              plot.addLinePlot("my plot", x, y);
              JFrame frame1 = new JFrame("a plot panel");
              frame1.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                  frame.show();
                  e.getWindow().dispose();
                }
              });
              frame1.setSize(500,300);
              frame1.setContentPane(plot);
              frame1.setVisible(true);
              graphing.hide();
              graph_m = 0.0;
              graph_b = 0.0;
              graph_var.setText(" = ");
              graph_textBox.setText("");
              graph_formula.setText("y = mx + b");
              graphing.remove(graph_panel);
              graphing.add(centerPane);
              frame1.show();
            }
          }
        }
          //catch unplottable graphs
        catch(Exception ea){
          ea.printStackTrace();
          graph_textBox.setText("");
        }
      }
    });
    JButton clos = new JButton("Close");
    clos.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.show();
        graph_m = 0.0;
        graph_b = 0.0;
        graph_var.setText(" = ");
        graph_textBox.setText("");
        graph_formula.setText("y = mx + b");
        graphing.remove(graph_panel);
        graphing.add(centerPane);
        graphing.hide();
      }
    });

    //adds displays
    graph_bottom.add(sub, BorderLayout.WEST);
    graph_bottom.add(clos, BorderLayout.EAST);
    graphing.add(graph_bottom, BorderLayout.SOUTH);
    graphing.hide();
    
    // keyListener class
    MyKeyListener myKeyListener = new MyKeyListener();    

    // setup frame
    frame = new JFrame("Caluclator");
    frame.setSize(500, 300);
    frame.setLocation(5, 5);
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    // setup label
    JLabel jlab = new JLabel("Jason Calc!");
    jlab.setFont(new Font("Serif", Font.BOLD, 20));
    jlab.setHorizontalAlignment(SwingConstants.CENTER);
    frame.add(jlab, BorderLayout.NORTH);

    // setup display
    JPanel display = new JPanel();
    num = new JTextField();
    num.setEditable(false);
    num.addKeyListener(myKeyListener);
    num.setText("");
    num.setHorizontalAlignment(SwingConstants.CENTER);
    num.setPreferredSize(new Dimension(1000, 100));
    num.setFont(new Font("Arial", Font.BOLD, 18));
    num.setVisible(true);
    display.add(num);
    frame.add(display, BorderLayout.NORTH);
    display.setVisible(true);

    JPanel funcs = new JPanel();
    funcs.setMinimumSize(new Dimension(500,500));
    funcs.setLayout(new GridLayout(2,4,3,3));
    funcs.setVisible(false);

    JPanel panel = new JPanel();
    panel.setMinimumSize(new Dimension(500, 500));
    panel.setLayout(new GridLayout(5, 4, 3, 3));
    panel.setVisible(true);
    // factorial button
    bf1 = new JButton("x!");
    bf1.setBackground(new Color(133, 131, 131));
    bf1.setForeground(new Color(255, 255, 255));
    bf1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + "!";
        displayeq = displayeq + "!";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // ln button
    bf2 = new JButton("ln");
    bf2.setBackground(new Color(133, 131, 131));
    bf2.setForeground(new Color(255, 255, 255));
    bf2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        bc++;
        equation = equation + "Math.log(";
        displayeq = displayeq + "ln(";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // log button
    bf3 = new JButton("log");
    bf3.setBackground(new Color(133, 131, 131));
    bf3.setForeground(new Color(255, 255, 255));
    bf3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        bc++;
        equation = equation + "log10(";
        displayeq = displayeq + "log(";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // square root button
    bf4 = new JButton("√");
    bf4.setBackground(new Color(133, 131, 131));
    bf4.setForeground(new Color(255, 255, 255));
    bf4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        bc++;
        equation = equation + "Math.sqrt(";
        displayeq = displayeq + "√(";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // power button
    bf5 = new JButton("^");
    bf5.setBackground(new Color(133, 131, 131));
    bf5.setForeground(new Color(255, 255, 255));
    bf5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + "^";
        displayeq = displayeq + "^";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // set calculator to degrees
    bf6 = new JButton("Func");
    bf6.setBackground(new Color(133, 131, 131));
    bf6.setForeground(new Color(255, 255, 255));
    bf6.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        funcs.show();
        panel.hide();
      }
    });

    // sin button
    bf7 = new JButton("sin");
    bf7.setBackground(new Color(133, 131, 131));
    bf7.setForeground(new Color(255, 255, 255));
    bf7.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        bc++;
        if (inverse == false) {
          equation = equation + "sin(";
          displayeq = displayeq + "sin(";
        } else {
          equation = equation + "asin(";
          displayeq = displayeq + "asin(";
        }
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // cos button
    bf8 = new JButton("cos");
    bf8.setBackground(new Color(133, 131, 131));
    bf8.setForeground(new Color(255, 255, 255));
    bf8.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        bc++;
        if (inverse == false) {
          equation = equation + "cos(";
          displayeq = displayeq + "cos(";
        } else {
          equation = equation + "acos(";
          displayeq = displayeq + "acos(";
        }
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // tan button
    bf9 = new JButton("tan");
    bf9.setBackground(new Color(133, 131, 131));
    bf9.setForeground(new Color(255, 255, 255));
    bf9.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        bc++;
        if (inverse == false) {
          equation = equation + "tan(";
          displayeq = displayeq + "tan(";
        } else {
          equation = equation + "atan(";
          displayeq = displayeq + "atan(";
        }
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // natural exponential button
    bf10 = new JButton("EXP");
    bf10.setBackground(new Color(133, 131, 131));
    bf10.setForeground(new Color(255, 255, 255));
    bf10.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + "E";
        displayeq = displayeq + "E";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });
    
    // set calculator to radians
    bf11 = new JButton("Rad");
    bf11.setBackground(new Color(133, 131, 131));
    bf11.setForeground(new Color(255, 255, 255));
    bf11.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(raddeg==false){
          raddeg = true;
          bf11.setText("Rad");
        }
        else{
          raddeg = false;
          bf11.setText("Deg");
        }
      }
    });

    // inverse button
    bf12 = new JButton("Inv");
    bf12.setBackground(new Color(133, 131, 131));
    bf12.setForeground(new Color(255, 255, 255));
    bf12.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (inverse == false) {
          inverse = true;
          bf12.setBackground(new Color(97, 97, 97));
          bf7.setText("asin");
          bf8.setText("acos");
          bf9.setText("atan");
          bf15.setText("Rnd");
        } else {
          inverse = false;
          bf12.setBackground(new Color(133, 131, 131));
          bf7.setText("sin");
          bf8.setText("cos");
          bf9.setText("tan");
          bf15.setText("ANS");
        }
        num.setText(displayeq);
        num.setVisible(true);
      }
    });

    // pi button
    bf13 = new JButton("π");
    bf13.setBackground(new Color(133, 131, 131));
    bf13.setForeground(new Color(255, 255, 255));
    bf13.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + "3.14159265358979";
        displayeq = displayeq + "π";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // euler's number button
    bf14 = new JButton("e");
    bf14.setBackground(new Color(133, 131, 131));
    bf14.setForeground(new Color(255, 255, 255));
    bf14.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + "2.718281828459045";
        displayeq = displayeq + "e";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // previous answer button
    bf15 = new JButton("ANS");
    bf15.setBackground(new Color(133, 131, 131));
    bf15.setForeground(new Color(255, 255, 255));
    bf15.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (inverse == false) {
          equation = equation + preveq;
          displayeq = displayeq + "ANS";
        } else {
          double rand = Math.random();
          equation = equation + Math.round(rand * 10000000.0) / 10000000.0;
          displayeq = displayeq + Math.round(rand * 10000000.0) / 10000000.0;
        }
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // modulos button
    b1 = new JButton("%");
    b1.setBackground(new Color(133, 131, 131));
    b1.setForeground(new Color(255, 255, 255));
    b1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + "%";
        displayeq = displayeq + "%";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // front bracket button
    b2 = new JButton("(");
    b2.setBackground(new Color(133, 131, 131));
    b2.setForeground(new Color(255, 255, 255));
    b2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        bc++;
        equation = equation + "(";
        displayeq = displayeq + "(";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // back bracket button
    b3 = new JButton(")");
    b3.setBackground(new Color(133, 131, 131));
    b3.setForeground(new Color(255, 255, 255));
    b3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (bc > 0) {
          bc--;
          equation = equation + ")";
          displayeq = displayeq + ")";
          String a = displayeq;
          for (int x = 0; x < bc; x++) {
            a = a + ")";
          }
          num.setText(a);
        }
        num.setVisible(true);
      }
    });

    // clear button
    b4 = new JButton("AC");
    b4.setBackground(new Color(133, 131, 131));
    b4.setForeground(new Color(255, 255, 255));
    b4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        bc = 0;
        equation = "";
        displayeq = "";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    b5 = new JButton("7");
    b5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + "7";
        displayeq = displayeq + "7";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    b6 = new JButton("8");
    b6.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + "8";
        displayeq = displayeq + "8";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    b7 = new JButton("9");
    b7.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + "9";
        displayeq = displayeq + "9";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // division button
    b8 = new JButton("/");
    b8.setBackground(new Color(133, 131, 131));
    b8.setForeground(new Color(255, 255, 255));
    b8.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + "/";
        displayeq = displayeq + "/";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    b9 = new JButton("4");
    b9.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + "4";
        displayeq = displayeq + "4";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    b10 = new JButton("5");
    b10.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + "5";
        displayeq = displayeq + "5";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    b11 = new JButton("6");
    b11.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + ("6");
        displayeq = displayeq + "6";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // multiplication button
    b12 = new JButton("*");
    b12.setBackground(new Color(133, 131, 131));
    b12.setForeground(new Color(255, 255, 255));
    b12.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + ("*");
        displayeq = displayeq + "*";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    b13 = new JButton("1");
    b13.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + ("1");
        displayeq = displayeq + "1";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    b14 = new JButton("2");
    b14.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation += ("2");
        displayeq = displayeq + "2";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    b15 = new JButton("3");
    b15.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation += ("3");
        displayeq = displayeq + "3";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    // subtraction button
    b16 = new JButton("-");
    b16.setBackground(new Color(133, 131, 131));
    b16.setForeground(new Color(255, 255, 255));
    b16.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation += ("-");
        displayeq = displayeq + "-";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    b17 = new JButton("0");
    b17.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation += ("0");
        displayeq = displayeq + "0";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    b18 = new JButton(".");
    b18.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + ".";
        displayeq = displayeq + ".";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });

    b19 = new JButton("=");
    b19.setBackground(new Color(59, 89, 182));
        b19.setForeground(Color.WHITE);
        b19.setFocusPainted(false);
        b19.setFont(new Font("Tahoma", Font.BOLD, 12));
    b19.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (bc > 0) {
          for (int x = 1; x <= bc; x++) {
            equation = equation + ")";
          }
          String ubg = calculate(equation);
          if (ubg == "NaN") {
            ubg = "Math Error";
          }
          bc = 0;
          num.setText(ubg);
          preveq = ubg;
          num.setVisible(true);
          equation = "";
          displayeq = "";
        } else if (bc == 0) {
          String ubg = calculate(equation);
          if (ubg.equals("Syntax Error")) {
            num.setText("Syntax Error");
          } else {
            num.setText(ubg);
          }
          num.setVisible(true);
          equation = "";
          displayeq = "";
        } else {
          num.setText("Syntax Error");
          num.setVisible(true);
          equation = "";
          displayeq = "";
        }
      }
    });

    b20 = new JButton("+");
    b20.setBackground(new Color(133, 131, 131));
    b20.setForeground(new Color(255, 255, 255));
    b20.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        equation = equation + "+";
        displayeq = displayeq + "+";
        String a = displayeq;
        for (int x = 0; x < bc; x++) {
          a = a + ")";
        }
        num.setText(a);
        num.setVisible(true);
      }
    });
    panel.add(bf11);
    panel.add(bf6);
    panel.add(bf1);
    panel.add(b1);
    panel.add(b2);
    panel.add(b3);
    panel.add(b4);
    panel.add(bf12);
    panel.add(bf7);
    panel.add(bf2);
    panel.add(b5);
    panel.add(b6);
    panel.add(b7);
    panel.add(b8);
    panel.add(bf13);
    panel.add(bf8);
    panel.add(bf3);
    panel.add(b9);
    panel.add(b10);
    panel.add(b11);
    panel.add(b12);
    panel.add(bf14);
    panel.add(bf9);
    panel.add(bf4);
    panel.add(b13);
    panel.add(b14);
    panel.add(b15);
    panel.add(b16);
    panel.add(bf15);
    panel.add(bf10);
    panel.add(bf5);
    panel.add(b17);
    panel.add(b18);
    panel.add(b19);
    panel.add(b20);

    //pi button
    pi = new JFrame();
    pi.setLayout(new BorderLayout());
    JLabel accuracy = new JLabel("Accuracy:");
    JTextField pi_textBox = new JTextField(10);
    pi_textBox.setSize(40, 10);
    JPanel pi_panelMiddle = new JPanel();
    JLabel pi_formula = new JLabel("π ≈ 4 * ((# of √((2 * x - 1) + (2 * y - 1)) < 1) / n ) ≈");
    pi_panelMiddle.add(accuracy);
    pi_formula.setHorizontalAlignment(SwingConstants.CENTER);
    pi_panelMiddle.add(pi_textBox);
    JButton pi_submit = new JButton("Calculate");
    JButton pi_close = new JButton("Close");
    JPanel pi_panelBottom = new JPanel();
    pi_panelBottom.setLayout(new GridLayout());
    pi_panelBottom.add(pi_submit);
    JButton smort_pi = new JButton("Learn");
    JFrame smort_pg_pi = new JFrame("Learn");
    smort_pg_pi.setLayout(new BorderLayout());
    JTextArea displayt_pi = new JTextArea(16,58);
    //learn pi
    displayt_pi.setText("Estimating Pi\nPi is used to find the area and circumference of a circle. \nThe first 5 digits are 3.1415... and since it is irrational, it \nextends forever. \nNow how can we find Pi? Well, first imagine a 4 quadrant \ngraph that extends up 1, left 1, right 1, down 1, for a total \narea of 4. Now using using a certain amount of pairs of \nrandom numbers from -1 to 1, you can plot points on this \ngraph. Given the points, find the hypotenuse using \npythagorean's theorum. After they are all placed, find the \nnumber of points in the circle. Then since the formula for \nthe area of a circle is pi times r squared, and the area of \nthe square is 4 (2*2), if you divide the numbre of dots in \nthe area in the circle times 4 divded by number of the points in \n the square, you will find pi, which is what we are solving for.");
    displayt_pi.setEditable(false); // set textArea non-editable
    Font myFont_pi = new Font("Serif", Font.BOLD,   14);
    displayt_pi.setFont(myFont_pi);
    JScrollPane scroll_pi = new JScrollPane(displayt_pi);
    scroll_pi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    smort_pg_pi.add(scroll_pi, BorderLayout.EAST);
    smort_pg_pi.add(displayt_pi, BorderLayout.WEST);
    smort_pg_pi.setSize(500,300);
    smort_pi.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent er) {
        smort_pg_pi.show();
      }
    });
    pi_panelBottom.add(smort_pi);
    pi_panelBottom.add(pi_close);
    pi_close.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        pi_formula.setText("π ≈ 4 * ((# of √((2 * x - 1) + (2 * y - 1)) < 1) / n ) ≈");
        pi_textBox.setText("");
        pi.hide();
        frame.show();
      }
    });
    //estimate pi
    pi_submit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        numThrows=Math.round(Integer.parseInt(pi_textBox.getText()));
        Random randomGen = new Random (System.currentTimeMillis());
        int hits = 0;
        double PI = 0;     
        for (int i = 0; i < numThrows; i++){ 
          double x = (randomGen.nextDouble()) * 2 - 1.0;
          double y = (randomGen.nextDouble()) * 2 - 1.0;
          if (isInside(x, y)){
            hits++;
          }
        }
        double dthrows = numThrows;
        PI = (double)(4.0 * (hits/dthrows));
        System.out.println(PI);
        pi_formula.setText("π ≈ 4 * ((# of √((2 * x - 1) + (2 * y - 1)) < 1) / "+pi_textBox.getText()+" ) ≈"+PI);
        preveq = String.valueOf(PI);
      }
    });
    pi.add(pi_formula, BorderLayout.CENTER);
    pi.add(pi_panelMiddle, BorderLayout.NORTH);
    pi.add(pi_panelBottom, BorderLayout.SOUTH);
    pi.setSize(500, 300);
    pi.setLocationRelativeTo(null);
    pi.hide();

    //pythagorean theorum hypotenuese button
    JFrame pyth_s = new JFrame();
    pyth_s.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    pyth_s.setSize(500,300);
    pyth_s.setLayout(new BorderLayout());
    JLabel pyth_sl_a = new JLabel("Side A:");
    JLabel pyth_formula_a = new JLabel("c = √(a^2 + b^2) ≈ ");
    pyth_formula_a.setHorizontalAlignment(JLabel.CENTER);
    JPanel pyth_s_center = new JPanel();
    picLabel_s.setIcon(new ImageIcon(new ImageIcon("pythag1.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
    pyth_s_center.add(pyth_formula_a, BorderLayout.NORTH);
    pyth_s_center.add(picLabel_s, BorderLayout.CENTER);
    pyth_s.add(pyth_s_center, BorderLayout.CENTER);
    JTextField pyth_s_txt = new JTextField(10);
    pyth_sl_a.setSize(40,10);
    JPanel pyth_s_up = new JPanel();
    pyth_s_up.add(pyth_sl_a);
    pyth_s_up.add(pyth_s_txt);
    pyth_s.add(pyth_s_up, BorderLayout.NORTH);
    JButton pyth_s_submit = new JButton("Submit");
    JButton pyth_s_close = new JButton("Close");
    pyth_s_close.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent er){
        pyth_formula_a.setText("c = √(a^2 + b^2) ≈ ");
        pyth_a = 0.0;
        pyth_b = 0.0;
        pyth_c = 0.0;
        pyth_s_txt.setText("");
        pyth_s.hide();
        frame.show();
      }
    });
    JPanel pyth_s_panelBottom = new JPanel();
    pyth_s_panelBottom.setLayout(new GridLayout());
    pyth_s_panelBottom.add(pyth_s_submit);
    JButton smort_ps = new JButton("Learn");
    JFrame smort_pg_ps = new JFrame("Learn");
    smort_pg_ps.setLayout(new BorderLayout());
    JTextArea displayt_ps = new JTextArea(16,58);
    //learn pythag
    displayt_ps.setText("Pythagorean Theorum \n \nThe pythagorean theorum is a very useful formula that can \ncalculate sides of a triangle. The formula is a^2 + b^2 =\nc^2. But why is that?\n\nWell contrary to what you might think, pythagorean \ntheorum is not a formula about triangles, but about squares.\n\nThis is actually proven because by taking a square with side\nlength c and rotating it some degrees so that the vertices \nare touching the sides of a larger square with side length \na+b, a and b being divided where the smaller square \ntouches the larger square, the squares will naturally appear \nwhen considering the area of a square.");
    displayt_ps.setEditable(false); // set textArea non-editable
    Font myFont_ps = new Font("Serif", Font.BOLD,   14);
    displayt_ps.setFont(myFont_ps);
    JScrollPane scroll_ps = new JScrollPane(displayt_ps);
    scroll_ps.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    smort_pg_ps.add(scroll_ps, BorderLayout.EAST);
    smort_pg_ps.add(displayt_ps, BorderLayout.WEST);
    smort_pg_ps.setSize(500,300);
    smort_ps.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent er) {
        smort_pg_ps.show();
      }
    });
    pyth_s_panelBottom.add(smort_ps);
    pyth_s_panelBottom.add(pyth_s_close);
    pyth_s.add(pyth_s_panelBottom, BorderLayout.SOUTH);
    //find pythag
    pyth_s_submit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(pyth_sl_a.getText()=="Side A:"){
          try{
          pyth_a = Double.parseDouble(pyth_s_txt.getText());
          pyth_sl_a.setText("Side B:");
          pyth_s_txt.setText("");
          System.out.println(pyth_a);
          pyth_formula_a.setText("c = √("+pyth_a+"^2 + b^2) ≈ ");
          picLabel_s.setIcon(new ImageIcon(new ImageIcon("pythag2.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
          }
          catch(Exception er){
          pyth_sl_a.setText("Side A:");
          pyth_s_txt.setText("");
          pyth_formula_a.setText("c = √(a^2 + b^2) ≈ ");
        }
        }
        else if(pyth_sl_a.getText()=="Side B:"){
        try{
          pyth_b = Double.parseDouble(pyth_s_txt.getText());
          pyth_sl_a.setText("Side C:");
          pyth_c = Math.round(1000.0*Math.sqrt(pyth_a*pyth_a+pyth_b*pyth_b))/1000.0;
          pyth_s_txt.setText(String.valueOf(pyth_c));
          pyth_formula_a.setText("c = √("+pyth_a+"^2 + "+pyth_b+"^2) ≈ "+pyth_c);
          System.out.println(pyth_b);
          pyth_s_submit.setText("Close");
          preveq = String.valueOf(pyth_c);
          picLabel_s.setIcon(new ImageIcon(new ImageIcon("pythag3.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        }
        catch(Exception er){
          pyth_sl_a.setText("Side B:");
          pyth_s_txt.setText("");
          pyth_formula_a.setText("c = √("+pyth_a+"^2 + b^2) ≈ ");
        }
          System.out.println(Math.sqrt(1000*pyth_a*pyth_a+1000*pyth_b*pyth_b));
        }
        else if(pyth_sl_a.getText()=="Side C:"){
          pyth_a = 0.0;
          pyth_b = 0.0;
          pyth_c = 0.0;
          pyth_sl_a.setText("Side A:");
          pyth_s_txt.setText("");
          pyth_formula_a.setText("c = √(a^2 + b^2) ≈ ");
          pyth_s_submit.setText("Submit");
          picLabel_s.setIcon(new ImageIcon(new ImageIcon("pythag1.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
          pyth_s.hide();
          frame.show();
        }
      }
    });

    //pythagorean button to find sides
    JFrame pyth_h = new JFrame();
    pyth_h.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    pyth_h.setSize(500,300);
    pyth_h.setLayout(new BorderLayout());
    JLabel pyth_hl_a = new JLabel("Side A:");
    JTextField pyth_h_txt = new JTextField(10);
    pyth_hl_a.setSize(40,10);
    JLabel pyth_formula_h = new JLabel("b = √(c^2 - a^2) ≈ ");
    pyth_formula_h.setHorizontalAlignment(JLabel.CENTER);
    JPanel pyth_h_center = new JPanel();
    pyth_h_center.setLayout(new BorderLayout());
    picLabel_h.setIcon(new ImageIcon(new ImageIcon("pythag1.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
    pyth_h_center.add(pyth_formula_h, BorderLayout.NORTH);
    pyth_h_center.add(picLabel_h, BorderLayout.CENTER);
    pyth_h.add(pyth_h_center, BorderLayout.CENTER);
    JPanel pyth_h_up = new JPanel();
    pyth_h_up.add(pyth_hl_a);
    pyth_h_up.add(pyth_h_txt);
    pyth_h.add(pyth_h_up, BorderLayout.NORTH);
    JButton pyth_h_submit = new JButton("Submit");
    JButton pyth_h_close = new JButton("Close");
    pyth_h_close.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent er){
        pyth_formula_h.setText("c = √(a^2 + b^2) ≈ ");
        pyth_a = 0.0;
        pyth_b = 0.0;
        pyth_c = 0.0;
        pyth_h_txt.setText("");
        pyth_h.hide();
        frame.show();
      }
    });
    JPanel pyth_h_panelBottom = new JPanel();
    pyth_h_panelBottom.setLayout(new GridLayout());
    pyth_h_panelBottom.add(pyth_h_submit);
    JButton smort_pyth_h = new JButton("Learn");
    JFrame smort_pg_pyth_h = new JFrame("Learn");
    smort_pg_pyth_h.setLayout(new BorderLayout());
    //learn pythag
    JTextArea displayt_pyth_h = new JTextArea(16,58);
    displayt_pyth_h.setText("Pythagorean Theorum \n \nThe pythagorean theorum is a very useful formula that can \ncalculate sides of a triangle. The formula is a^2 + b^2 =\nc^2. But why is that?\n\nWell contrary to what you might think, pythagorean \ntheorum is not a formula about triangles, but about squares.\n\nThis is actually proven because by taking a square with side\nlength c and rotating it some degrees so that the vertices \nare touching the sides of a larger square with side length \na+b, a and b being divided where the smaller square \ntouches the larger square, the squares will naturally appear \nwhen considering the area of a square.");
    displayt_pyth_h.setEditable(false); // set textArea non-editable
    Font myFont_pyth_h = new Font("Serif", Font.BOLD,   14);
    displayt_pyth_h.setFont(myFont_pyth_h);
    JScrollPane scroll_pyth_h = new JScrollPane(displayt_pyth_h);
    scroll_pyth_h.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    smort_pg_pyth_h.add(scroll_pyth_h, BorderLayout.EAST);
    smort_pg_pyth_h.add(displayt_pyth_h, BorderLayout.WEST);
    smort_pg_pyth_h.setSize(500,300);
    smort_pyth_h.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent er) {
        smort_pg_pyth_h.show();
      }
    });
    //calculating pythagorean side
    pyth_h_panelBottom.add(smort_pyth_h);
    pyth_h_panelBottom.add(pyth_h_close);
    pyth_h.add(pyth_h_panelBottom, BorderLayout.SOUTH);
    pyth_h_submit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(pyth_hl_a.getText()=="Side A:"){
          try{
          pyth_a = Double.parseDouble(pyth_h_txt.getText());
          pyth_hl_a.setText("Side C:");
          pyth_h_txt.setText("");
          pyth_formula_h.setText("b = √(c^2 - "+pyth_a+"^2) ≈ ");
          picLabel_h.setIcon(new ImageIcon(new ImageIcon("pythag3.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));

          }
          catch(Exception er){
            pyth_hl_a.setText("Side A:");
            pyth_h_txt.setText("");
            pyth_formula_h.setText("b = √(c^2 - a^2) ≈ ");
          }
        }
        else if(pyth_hl_a.getText()=="Side C:"){
          try{
          pyth_c = Double.parseDouble(pyth_h_txt.getText());
          pyth_hl_a.setText("Side B:");
          pyth_b = Math.round(1000.0*Math.sqrt(pyth_c*pyth_c-pyth_a*pyth_a))/1000.0;
          pyth_h_txt.setText(String.valueOf(pyth_b));
          System.out.println(1000.0*Math.sqrt(pyth_c*pyth_c-pyth_a*pyth_a));
          pyth_h_submit.setText("Submit");
          pyth_formula_h.setText("b = √("+pyth_c+"^2 - "+pyth_a+"^2) ≈ "+pyth_b);
          preveq = String.valueOf(pyth_b);
          picLabel_h.setIcon(new ImageIcon(new ImageIcon("pythag2.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
          }
          catch(Exception er){
            pyth_hl_a.setText("Side C:");
            pyth_h_txt.setText("");
            pyth_formula_h.setText("b = √(c^2 - "+pyth_a+"^2) ≈ ");
          }
        }
      }
    });

    //euler's button
    e = new JFrame("Euler's");
    e.setLayout(new BorderLayout());
    JLabel e_accuracy = new JLabel("Accuracy (n):");
    JTextField e_textBox = new JTextField(10);
    e_textBox.setSize(40, 10);
    JPanel e_panelMiddle = new JPanel();
    JLabel e_formula = new JLabel("e = 1 + (1/2!) + (1/3!) + (1/4!) ... 1/(n!)");
    e_panelMiddle.add(e_accuracy);
    e_formula.setHorizontalAlignment(SwingConstants.CENTER);
    e_panelMiddle.add(e_textBox);
    JButton e_submit = new JButton("Calculate");
    JButton e_close = new JButton("Close");
    e_close.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent er){
        e_formula.setText("e = 1 + (1/2!) + (1/3!) + (1/4!) ... 1/(n!)");
        e_textBox.setText("");
        e.hide();
        frame.show();
      }
    });
    JPanel e_panelBottom = new JPanel();
    e_panelBottom.setLayout(new GridLayout());
    e_panelBottom.add(e_submit);
    e_panelBottom.add(e_close);
    //calculate eulers
    e_submit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        double eu = 1.0;
        double n = Double.parseDouble(e_textBox.getText());
        if(n<=34.0 && n>0.0){
          for(int x = 1; x < n; x++){
            int fact = 1;  
            for(int i = 1 ; i <= x; i++){
              fact=fact*i;
            }
            System.out.println(fact);
            eu+=1.0/fact;
            System.out.println(eu);
          }
          eu = Math.round(eu *10000000000.0)/10000000000.0;
          e_formula.setText("e = 1 + 1 + (1/2!) + (1/3!) + (1/4!) ... 1/("+n+"!) ≈ "+eu);
          preveq=String.valueOf(eu);
        }
        else if(n>34.0){
          e_formula.setText("e = 1 + 1 + (1/2!) + (1/3!) + (1/4!) ... 1/("+n+"!) ≈ "+2.7182818);
          preveq=String.valueOf(2.7182818);
        }
        else if(n<=0.0){
          e_formula.setText("e = 1 + 1 + (1/2!) + (1/3!) + (1/4!) ... 1/("+n+"!) ≈ "+0);
        }
      }
    });
    e.add(e_formula, BorderLayout.CENTER);
    e.add(e_panelMiddle, BorderLayout.NORTH);
    e.add(e_panelBottom, BorderLayout.SOUTH);
    e.setSize(500, 300);
    e.setLocationRelativeTo(null);
    e.hide();


    //prime sieve button
    JFrame sieve = new JFrame("Sieve of Eratosthenes");
    sieve.setSize(500,300);
    sieve.setLayout(new BorderLayout());
    JLabel sieve_accuracy = new JLabel("Primes less than (n):");
    JTextField sieve_textBox = new JTextField(10);
    JPanel sieve_upper = new JPanel();
    sieve_upper.add(sieve_accuracy);
    sieve_upper.add(sieve_textBox);
    sieve.add(sieve_upper, BorderLayout.NORTH);
    sieve_textBox.setSize(40, 10);
    JLabel prime = new JLabel();
    prime.setHorizontalAlignment(JLabel.CENTER);
    sieve.add(prime, BorderLayout.CENTER);
    JButton sieve_submit = new JButton("Calculate");
    JButton sieve_close = new JButton("Close");
    sieve_close.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent er){
        sieve_textBox.setText("");
        sieve.hide();
        frame.show();
      }
    });
    JPanel sieve_panelBottom = new JPanel();
    sieve_panelBottom.setLayout(new GridLayout());
    sieve_panelBottom.add(sieve_submit);
    sieve_panelBottom.add(sieve_close);
    sieve.add(sieve_panelBottom, BorderLayout.SOUTH);
    //finding primes less than
    sieve_submit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        try{
          double sn = Double.parseDouble(sieve_textBox.getText());
          boolean primes[] = new boolean[(int)sn];
          for(int i = 0; i < sn; i++){
            primes[i] = true;
          }
          for(int p = 2; p * p < (int)sn; p++){
            System.out.println(p);
            if(primes[p]==true){
              for (int i = p * p; i < (int)sn; i += p){
                primes[i] = false;
              }
            }
          }
          prime.setText("");
          int lastprime = 0;
          for (int x = 2; x <= (int)sn; x++) {
            if(primes[x]==true && x!=2){
              prime.setText(prime.getText()+", "+x);
              System.out.println(x);
            }
            else if(primes[x]==true){
              prime.setText(prime.getText()+x);
              System.out.println(x);
            }
          }
          System.out.println(lastprime);
          prime.setText(prime.getText()+"..."+lastprime);
        }
        catch(Exception er){
        }
      }
    });
    sieve.setLocationRelativeTo(null);
    sieve.hide();

    //buttons display for function
    
    p1 = new JButton("Pythag Side");
    p1.setBackground(new Color(133, 131, 131));
    p1.setForeground(new Color(255, 255, 255));
    p1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pyth_formula_h.setText("b = √(c^2 - a^2) ≈ ");
        pyth_hl_a.setText("Side A:");
        picLabel_h.setIcon(new ImageIcon(new ImageIcon("pythag1.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        frame.hide();
        pyth_h.show();
      }
    });

    p2 = new JButton("Pythag Hyp");
    p2.setBackground(new Color(133, 131, 131));
    p2.setForeground(new Color(255, 255, 255));
    p2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pyth_formula_a.setText("c = √(a^2 + b^2) ≈ ");
        pyth_sl_a.setText("Side A:");
        picLabel_s.setIcon(new ImageIcon(new ImageIcon("pythag1.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        frame.hide();
        pyth_s.show();
      }
    });
    
    f2 = new JButton("Pi");
    f2.setBackground(new Color(133, 131, 131));
    f2.setForeground(new Color(255, 255, 255));
    f2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pi_textBox.setText("");
        pi_formula.setText("π ≈ 4 * ((# of √((2 * x - 1) + (2 * y - 1)) < 1) / "+pi_textBox.getText()+" n) ≈");
        pi.show();
      }
    });

    f3 = new JButton("Euler's");
    f3.setBackground(new Color(133, 131, 131));
    f3.setForeground(new Color(255, 255, 255));
    f3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent er) {
        e.show();
      }
    });

    f4 = new JButton("Prime Sieve");
    f4.setBackground(new Color(133, 131, 131));
    f4.setForeground(new Color(255, 255, 255));
    f4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent er) {
        sieve.show();
      }
    });

    f5 = new JButton("Graphing");
    f5.setBackground(new Color(133, 131, 131));
    f5.setForeground(new Color(255, 255, 255));
    f5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent er) {
        centerPane.show();
        graphing.show();
        frame.hide();
      }
    });

    //learn button

    JButton smort = new JButton("Learn");
    JFrame smort_pg = new JFrame("Learn");
    smort_pg.setLayout(new BorderLayout());
    JTextArea displayt = new JTextArea(16,58);
    displayt.setText("What is the Learn Button? \n\nWhen using the formula calculations in the calculator, \nclicking the Learn button will allow you to get a more \nin-depth look at how the calculations work and why they \nare how they are.");
    displayt.setEditable(false); // set textArea non-editable
    Font myFont = new Font("Serif", Font.BOLD,   14);
    displayt.setFont(myFont);
    JScrollPane scroll = new JScrollPane(displayt);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    smort_pg.add(scroll, BorderLayout.EAST);
    smort_pg.add(displayt, BorderLayout.WEST);
    smort_pg.setSize(500,300);
    smort.setBackground(new Color(133, 131, 131));
    smort.setForeground(new Color(255, 255, 255));
    smort.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent er) {
        smort_pg.show();
      }
    });

    // set calculator to degrees
    funct = new JButton("Return");
    funct.setBackground(new Color(133, 131, 131));
    funct.setForeground(new Color(255, 255, 255));
    funct.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        funcs.hide();
        panel.show();
      }
    });

    funcs.add(p1);
    funcs.add(p2);
    funcs.add(f2);
    funcs.add(f3);
    funcs.add(f4);
    funcs.add(f5);
    funcs.add(smort);
    funcs.add(funct);
    frame.add(funcs, BorderLayout.CENTER);

    
    frame.add(panel, BorderLayout.SOUTH);
    frame.show();
  }

  public static boolean isInside (double xPos, double yPos){  
    double distance = Math.sqrt((xPos * xPos) + (yPos * yPos));
    return (distance < 1.0);
  }


  //calculation method that returns answer
  public static String calculate(String eq) {
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    try {
      if (eq.equals("") || eq.equals("Syntax Error")) {
      number = "0";
      } else {
        for (int x = 0; x < eq.length(); x++) {
          if (eq.charAt(x) == 'l' && eq.charAt(x + 1) == 'o' && eq.charAt(x + 2) == 'g' && eq.charAt(x + 3) == '1' && eq.charAt(x + 4) == '0' && eq.charAt(x + 5) == '(') {
            int eb = findforward(eq, x + 5);
            String a = calculate(eq.substring(x + 5, eb + 1));
            eq = eq.substring(0, x) + String.valueOf(Math.log10(Double.parseDouble(a))) + eq.substring(eb + 1, eq.length());
          }
          else if (eq.charAt(x) == '^') {
            String one = calculate(eq.substring(findback(eq, x - 1), x)); // findback returns position of first digit/bracket
            String two = calculate(eq.substring(x + 1, findforward(eq, x + 1) + 1)); // findforward returns position of last digit/bracket
            eq = eq.substring(0, findback(eq, x - 1)) + Math.pow(Double.parseDouble(one), Double.parseDouble(two)) + eq.substring(findforward(eq, x + 1) + 1, eq.length());
          }
          else if (eq.charAt(x) == '!') {
            String numz = calculate(eq.substring(findback(eq, x - 1), x));
            int i, fact = 1;
            for (i = 1; i <= Double.parseDouble(numz); i++) {
              fact = fact * i;
            }
            eq = eq.substring(0, findback(eq, x - 1)) + fact + eq.substring(x + 1, eq.length());
          }
          if (raddeg == true) { // RADIANS
            if (eq.charAt(x) == 's' && eq.charAt(x + 1) == 'i' && eq.charAt(x + 2) == 'n' && eq.charAt(x + 3) == '(') {
              String one = calculate(eq.substring(x + 3, findforward(eq, x + 3) + 1));
              double roundOff = Math.round(Math.sin(Double.parseDouble(one)) * 10000000.0) / 10000000.0;
              eq = eq.substring(0, x) + roundOff + eq.substring(findforward(eq, x + 3) + 1, eq.length());
            }
            else if (eq.charAt(x) == 'c' && eq.charAt(x + 1) == 'o' && eq.charAt(x + 2) == 's' && eq.charAt(x + 3) == '(') {
              String one = calculate(eq.substring(x + 3, findforward(eq, x + 3) + 1));
              double roundOff = Math.round(Math.cos(Double.parseDouble(one)) * 10000000.0) / 10000000.0;
              eq = eq.substring(0, x) + roundOff + eq.substring(findforward(eq, x + 3) + 1, eq.length());
            }
            else if (eq.charAt(x) == 't' && eq.charAt(x + 1) == 'a' && eq.charAt(x + 2) == 'n' && eq.charAt(x + 3) == '(') {
              String one = calculate(eq.substring(x + 3, findforward(eq, x + 3) + 1));
              double roundOff = Math.round(Math.tan((double)Double.parseDouble(one)) * 10000000.0) / 10000000.0;
              eq = eq.substring(0, x) + roundOff + eq.substring(findforward(eq, x + 3) + 1, eq.length());
            }
            else if (eq.charAt(x) == 'a' && eq.charAt(x + 1) == 's' && eq.charAt(x + 2) == 'i' && eq.charAt(x + 3) == 'n' && eq.charAt(x + 4) == '(') {
              String one = calculate(eq.substring(x + 4, findforward(eq, x + 4) + 1));
              double roundOff = Math.round(Math.asin(Double.parseDouble(one)) * 10000000.0) / 10000000.0;
              eq = eq.substring(0, x) + roundOff + eq.substring(findforward(eq, x + 4) + 1, eq.length());
            }
            else if (eq.charAt(x) == 'a' && eq.charAt(x + 1) == 'c' && eq.charAt(x + 2) == 'o' && eq.charAt(x + 3) == 's' && eq.charAt(x + 4) == '(') {
              String one = calculate(eq.substring(x + 4, findforward(eq, x + 4) + 1));
              double roundOff = Math.round(Math.acos(Double.parseDouble(one)) * 10000000.0) / 10000000.0;
              eq = eq.substring(0, x) + roundOff + eq.substring(findforward(eq, x + 4) + 1, eq.length());
            }
            else if (eq.charAt(x) == 'a' && eq.charAt(x + 1) == 't' && eq.charAt(x + 2) == 'a' && eq.charAt(x + 3) == 'n' && eq.charAt(x + 4) == '(') {
              String one = calculate(eq.substring(x + 4, findforward(eq, x + 4) + 1));
              double roundOff = Math.round(Math.atan(Double.parseDouble(one)) * 10000000.0) / 10000000.0;
              eq = eq.substring(0, x) + roundOff + eq.substring(findforward(eq, x + 4) + 1, eq.length());
            }
          } else if (raddeg == false) { // DEGREES
            if (eq.charAt(x) == 's' && eq.charAt(x + 1) == 'i' && eq.charAt(x + 2) == 'n' && eq.charAt(x + 3) == '(') {
              String one = calculate(eq.substring(x + 3, findforward(eq, x + 3) + 1));
              double inRadians = Math.toRadians(Double.parseDouble(one));
              double roundOff = Math.round(Math.sin(inRadians) * 10000000.0) / 10000000.0;
              eq = eq.substring(0, x) + roundOff + eq.substring(findforward(eq, x + 3) + 1, eq.length());
            }
            else if (eq.charAt(x) == 'c' && eq.charAt(x + 1) == 'o' && eq.charAt(x + 2) == 's' && eq.charAt(x + 3) == '(') {
              String one = calculate(eq.substring(x + 3, findforward(eq, x + 3) + 1));
              double inRadians = Math.toRadians(Double.parseDouble(one));
              double roundOff = Math.round(Math.cos(inRadians) * 10000000.0) / 10000000.0;
              eq = eq.substring(0, x) + roundOff + eq.substring(findforward(eq, x + 3) + 1, eq.length());
            }
            else if (eq.charAt(x) == 't' && eq.charAt(x + 1) == 'a' && eq.charAt(x + 2) == 'n' && eq.charAt(x + 3) == '(') {
              String one = calculate(eq.substring(x + 3, findforward(eq, x + 3) + 1));
              double inRadians = Math.toRadians(Double.parseDouble(one));
              double roundOff = Math.round(Math.tan(inRadians) * 10000000.0) / 10000000.0;
              eq = eq.substring(0, x) + roundOff + eq.substring(findforward(eq, x + 3) + 1, eq.length());
            }
            else if (eq.charAt(x) == 'a' && eq.charAt(x + 1) == 's' && eq.charAt(x + 2) == 'i'
                && eq.charAt(x + 3) == 'n' && eq.charAt(x + 4) == '(') {
              String one = calculate(eq.substring(x + 4, findforward(eq, x + 4) + 1));
              double inRadians = Math.toRadians(Double.parseDouble(one));
              double roundOff = Math.round(Math.asin(inRadians) * 10000000.0) / 10000000.0;
              eq = eq.substring(0, x) + roundOff + eq.substring(findforward(eq, x + 4) + 1, eq.length());
            }
            else if (eq.charAt(x) == 'a' && eq.charAt(x + 1) == 'c' && eq.charAt(x + 2) == 'o' && eq.charAt(x + 3) == 's' && eq.charAt(x + 4) == '(') {
              String one = calculate(eq.substring(x + 4, findforward(eq, x + 4) + 1));
              double inRadians = Math.toRadians(Double.parseDouble(one));
              double roundOff = Math.round(Math.acos(inRadians) * 10000000.0) / 10000000.0;
              eq = eq.substring(0, x) + roundOff + eq.substring(findforward(eq, x + 4) + 1, eq.length());
            }
            else if (eq.charAt(x) == 'a' && eq.charAt(x + 1) == 't' && eq.charAt(x + 2) == 'a' && eq.charAt(x + 3) == 'n' && eq.charAt(x + 4) == '(') {
              String one = calculate(eq.substring(x + 4, findforward(eq, x + 4) + 1));
              double inRadians = Math.toRadians(Double.parseDouble(one));
              double roundOff = Math.round(Math.atan(inRadians) * 10000000.0) / 10000000.0;
              eq = eq.substring(0, x) + roundOff + eq.substring(findforward(eq, x + 4) + 1, eq.length());
            }
          }
        }
        System.out.println(eq);
        number = String.valueOf(engine.eval(eq));
      }
    } catch (Exception e) {
      number = "Syntax Error";
      return number;
    }
    num.setVisible(true);
    System.out.println(number);
    if (Double.parseDouble(number) % 1 == 0) {
      return String.valueOf(Math.round(Double.parseDouble(number)));
    } else {
      return number;
    }
  }

  // find position of last digit/bracket
  public static int findforward(String eq, int x) {
    char a = eq.charAt(x);
    if (eq.charAt(x) != '(') {
      for (int y = x; y < eq.length(); y++) {
        if (!Character.isLetterOrDigit(eq.charAt(y)) && eq.charAt(y) != '.') {
          return y - 1;
        }
      }
    } else {
      int brackcount = 0;
      for (int y = x; y < eq.length(); y++) {
        if (eq.charAt(y) == '(') {
          brackcount++;
        } else if (eq.charAt(y) == ')') {
          brackcount--;
        }
        if (brackcount == 0) {
          return y;
        }
      }
    }
    return eq.length() - 1;
  }

  //finds the position of the first number/bracket to calculate
  //only used for exponents
  public static int findback(String eq, int x) {
    if (eq.charAt(x) != ')') {
      for (int y = x; y >= 0; y--) {
        if (!Character.isLetterOrDigit(eq.charAt(y)) && eq.charAt(y) != '.') {
          return y + 1;
        }
      }
    } else {
      int brackcount = 0;
      for (int y = x; y >= 0; y--) {
        if (eq.charAt(y) == ')') {
          brackcount++;
        } else if (eq.charAt(y) == '(') {
          brackcount--;
        }
        if (brackcount == 0) {
          return y;
        }
      }
    }
    return 0;
  }
}

//key listener class to check for keyboard typing
class MyKeyListener extends KeyAdapter {
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    if (key == KeyEvent.VK_S) {
      System.out.println("S Key Pressed");
      Main.bc++;
      if (Main.inverse == false) {
        Main.equation = Main.equation + "sin(";
        Main.displayeq = Main.displayeq + "sin(";
      } else {
        Main.equation = Main.equation + "asin(";
        Main.displayeq = Main.displayeq + "asin(";
      }
    } else if (key == KeyEvent.VK_C) {
      System.out.println("C Key Pressed");
      Main.bc++;
      if (Main.inverse == false) {
        Main.equation = Main.equation + "cos(";
        Main.displayeq = Main.displayeq + "cos(";
      } else {
        Main.equation = Main.equation + "acos(";
        Main.displayeq = Main.displayeq + "acos(";
      }
    } else if (key == KeyEvent.VK_T) {
      System.out.println("T Key Pressed");
      Main.bc++;
      if (Main.inverse == false) {
        Main.equation = Main.equation + "tan(";
        Main.displayeq = Main.displayeq + "tan(";
      } else {
        Main.equation = Main.equation + "atan(";
        Main.displayeq = Main.displayeq + "atan(";
      }
    } else if (key == KeyEvent.VK_A) {
      System.out.println("A Key Pressed");
      Main.equation = Main.equation + Main.preveq;
      Main.displayeq = Main.displayeq + "ANS";
    } else if (key == KeyEvent.VK_P) {
      System.out.println("P Key Pressed");
      Main.equation = Main.equation + "3.14159265358979";
      Main.displayeq = Main.displayeq + "π";
    } else if (key == KeyEvent.VK_E) {
      System.out.println("E Key Pressed");
      Main.equation = Main.equation + "2.71828182846";
      Main.displayeq = Main.displayeq + "e";
    } else if (key == KeyEvent.VK_E) {
      System.out.println("E Key Pressed");
      Main.equation = Main.equation + "2.71828182846";
      Main.displayeq = Main.displayeq + "e";
    } else if (key == KeyEvent.VK_Q) {
      Main.bc++;
      System.out.println("Q Key Pressed");
      Main.equation = Main.equation + "Math.sqrt";
      Main.displayeq = Main.displayeq + "√(";
    } else if (key == KeyEvent.VK_L) {
      Main.bc++;
      System.out.println("L Key Pressed");
      Main.equation = Main.equation + "log10(";
      Main.displayeq = Main.displayeq + "log(";
    } else if (key == KeyEvent.VK_1) {
      System.out.println("+ Key Pressed");
      Main.equation = Main.equation + "+";
      Main.displayeq = Main.displayeq + "+";
    }
    String a = Main.displayeq;
    for (int x = 0; x < Main.bc; x++) {
      a = a + ")";
    }
    Main.num.setText(a);
    Main.num.setVisible(true);
  }
}
