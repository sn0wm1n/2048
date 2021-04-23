import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class MainFrame extends JFrame implements KeyListener, MouseListener {
    int[][] data = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };

    public MainFrame() {
        init();
        paint();
        addKeyListener(this);
        setVisible(true);

    }


    //初始化界面
    public void init() {
        setSize(514, 530);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(3);
        setAlwaysOnTop(true);
    }
    public void out(){
        JButton jb = new JButton("放弃");
        jb.setBounds(0, 0, 100, 50);
        jb.addMouseListener(this);

        getContentPane().add(jb);
    }

    //新增小块
    public void ad() {
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[i][j] == 0) {
                    arr.add(String.valueOf(i) + String.valueOf(j));
                }
            }
        }
        if (arr.size() != 0) {
            Random r = new Random();
            int i = r.nextInt(arr.size());
            int j = (r.nextInt(2) + 1) * 2;
            String get = arr.get(i);
            int m = Integer.parseInt(String.valueOf(get.charAt(0)));
            int n = Integer.parseInt(String.valueOf(get.charAt(1)));
            data[m][n] = j;
        }

    }

    //色块安排
    public void paint() {
        getContentPane().removeAll();



        //添加随机块
        ad();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Icon ic = new ImageIcon("main\\icon\\icon-" + data[i][j] + ".png");
                JLabel jl = new JLabel(ic);
                jl.setBounds(50 + i * 100, 50 + j * 100, 103, 103);
                getContentPane().add(jl);
            }
        }
        //背景
        Icon ic2 = new ImageIcon("main\\icon\\icon-background.jpg");
        JLabel jl2 = new JLabel(ic2);
        jl2.setBounds(5, 5, 500, 500);
        getContentPane().add(jl2);

        getContentPane().repaint();
        setVisible(true);
    }


    //上移
    public void up(int[][] arr) {
        int[][] arr2 = new int[4][4];
        //逆时针旋转
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr2[3 - j][i] = arr[i][j];
            }
        }
        left(arr2);
        //顺时针旋转
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[j][3 - i] = arr2[i][j];
            }
        }
        paint();
    }

    //看数组
    public void show(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (j == 0) System.out.print(arr[i][j]);
                else System.out.print("," + arr[i][j]);
            }
            System.out.println();
        }
    }

    //左移
    public void left(int[][] arr) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] != 0) {
                    for (int k = j + 1; k < 4; k++) {
                        if (arr[i][k] != 0) {
                            if (arr[i][k] != arr[i][j]) {
                                k = 4;
                            } else {
                                arr[i][j] += arr[i][k];
                                arr[i][k] = 0;
                                j = k;
                            }
                        }
                    }
                }

            }

        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        if (arr[i][k] != 0) {
                            arr[i][j] = arr[i][k];
                            arr[i][k] = 0;
                            k = 4;
                        }
                    }
                }
            }

        }
        paint();
    }

    //右移
    public void right(int[][] arr) {
        int[][] arr2 = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr2[i][j] = arr[i][3 - j];
            }
        }
        left(arr2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = arr2[i][3 - j];
            }
        }
        paint();

    }

    //下移
    public void down(int[][] arr) {
        //顺时针旋转
        int[][] arr2 = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr2[j][3 - i] = arr[i][j];
            }
        }
        left(arr2);
        //逆时针旋转
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[3 - j][i] = arr2[i][j];
            }
        }
        paint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 37) up(data);
        else if (e.getKeyCode() == 39) down(data);
        else if (e.getKeyCode() == 38) left(data);
        else if (e.getKeyCode() == 40) right(data);
        else if (e.getKeyCode() == 74) {
            for (int i = 0; i < 4; i++)
                for (int j = 2; j < 4; j++) {
                    data[i][j] = 0;
                }
            paint();
        }
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j < 4; j++) {
                data[i][j] = 0;
            }
        }
        paint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
