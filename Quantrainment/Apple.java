import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Apple extends Applet implements MouseListener
{
    
    public Apple()
    {
    }
    
    int matrix[][]={{1,1,1,0,1,0,1,0,1,1,0,1,0,0,1,0},
                    {0,0,1,0,1,0,0,1,0,1,0,0,0,1,1,1},
                    {0,1,0,0,1,0,0,1,0,0,1,0,1,1,1,0},
                    {0,1,1,1,0,1,0,1,1,0,1,1,0,1,0,0},
                    {0,1,0,0,0,0,1,1,1,0,0,1,0,1,0,1},
                    {1,0,0,1,0,1,1,0,0,1,0,0,1,0,0,0},
                    {1,0,0,1,0,1,1,0,0,0,1,0,0,0,0,1},
                    {0,0,1,0,1,1,0,0,1,0,0,1,1,0,1,0},
                    {0,1,0,1,1,0,0,1,0,0,1,1,0,1,0,0},
                    {1,0,0,0,0,1,0,0,0,1,1,0,1,0,0,1},
                    {0,0,0,1,0,0,1,0,0,1,1,0,1,0,0,1},
                    {1,0,1,0,1,0,0,1,1,1,0,0,0,0,1,0},
                    {0,0,1,0,1,1,0,1,1,0,1,0,1,1,1,0},
                    {0,1,1,1,0,1,0,0,1,0,0,1,0,0,1,0},
                    {1,1,1,0,0,0,1,0,1,0,0,1,0,1,0,0},
                    {0,1,0,0,1,0,1,1,0,1,0,1,0,1,1,1}};
    int sol[]=new int[16];
    int p[]=new int[16];
    Button table[][];
    boolean state[][];
    boolean ngame[][];
    int moves;
    int randx , randy;
    int sizex , sizey;
    Panel P , P2 , P3; 
    Label Moves, Quant ; 
    Button Restart, Hint, Easy, Medium, Hard;
    GridLayout gl;
    int row = 4, col = 4;
    Font font;
    String mark = "X";
    int hint_counter = 2;

    public void start()
    {
        setLayout(new BorderLayout());
        gl = new GridLayout(row,col);
        P = new Panel(gl);
        font = new Font("ComicSans",Font.BOLD,21);
        setFont(font);
        P2 = new Panel(new BorderLayout());
        P3 = new Panel();
        moves = 0;
        Moves = new Label(Integer.toString(moves));
        Quant = new Label("Quantrainment");
        table = new Button[row][col];
        state = new boolean[row][col];
        ngame = new boolean[row][col];
        Restart = new Button("Restart");
        Restart.addMouseListener(this);
        Hint = new Button("Hint");
        Hint.addMouseListener(this);
        Easy = new Button("Easy");
        Easy.addMouseListener(this);
        Medium = new Button("Medium");
        Medium.addMouseListener(this);
        Hard = new Button("Hard");
        Hard.addMouseListener(this);
        int count=0;
        for(int x=0;x<row;x++)
        {
            for(int y=0;y<col;y++)
            {
                table[x][y]=new Button();
                table[x][y].addMouseListener(this);
                P.add(table[x][y]);
                state[x][y]=false;
                ngame[x][y]=false;
                p[count]=sol[count]=0;
                count++;
            }
        }
        add(P2, "North");
        add(P, "Center");
        P2.add(Moves, "West");
        P2.add(Quant, "Center");
        P2.add(P3, "North");
        P3.add(new Label("New Game"), "center");
        P3.add(Easy);
        P3.add(Medium);
        P3.add(Hard);
        P2.add(Hint,"East");
        P2.setBackground(Color.lightGray);
        Moves.setBackground(Color.lightGray);
        Moves.setForeground(Color.white);
        Quant.setBackground(Color.lightGray);
        Quant.setForeground(Color.black);
        sizex = 300;
        sizey = 346;
        Restart_Game(3);
    }
    
    public void Restart_Game(int mode)
    {
        setSize(sizex, sizey);
        invalidate();
        validate();
        gl.setRows(row);
        gl.setColumns(col);
        moves=0;
        Moves.setText(Integer.toString(moves));
        Hint.setEnabled(true);
        int count = 0;
        for(int x=0;x<row;x++)
        {
            for(int y=0;y<col;y++)
            {
                table[x][y].setEnabled(true);
                table[x][y].setLabel("");
                table[x][y].setBackground(Color.gray);
                table[x][y].setForeground(Color.white);
                P.add(table[x][y]);
                state[x][y]=false;
                ngame[x][y]=false;
                p[count]=0;
                sol[count]=0;
                count++;
            }
        }
        setSize(sizex,sizey);
        invalidate();
        validate();
        count = 0;
        int temp;
        if(mode==1)
        {
            temp = 3;
        }
        else if(mode==2)
        {
            temp=6;
        }
        else
        {
            temp = 10;
        }
        while(count < temp)
        {
            randx = (int)(Math.random()*row);
            randy = (int)(Math.random()*col);
            if(ngame[randx][randy]==false)
            {
                ngame[randx][randy]=true;
                state[randx][randy]=true;
                table[randx][randy].setLabel(mark);
                count++;
            }
        }
    }
    
    public void mouseClicked(MouseEvent e)
    {
        if(e.getSource()==Easy)
        {
            Restart_Game(1);
        }
        if(e.getSource()==Medium)
        {
            Restart_Game(2);
        }
        if(e.getSource()==Hard)
        {
            Restart_Game(3);
        }
        if(e.getSource()== Hint)
        {
            show_Hint();
        }
        for(int x=0;x<row;x++)
        {
            for(int y=0;y<col;y++)
            {
                if(e.getSource()==table[x][y])
                {
                    moves++;
                    Moves.setText(Integer.toString(moves));
                    if(x==0 && y==0 || x==3 && y==0 || x==0 && y==3 || x==3 && y==3)
                    {
                        change_state('c',x,y);
                    }
                    else if(x==0&&y>0&&y<3 || x==3&&y>0&&y<3 || y==0&&x>0&&x<3 || y==3&&x>0&&x<3)
                    {
                        change_state('e',x,y);
                    }
                    else
                    {
                        change_state('i',x,y);
                    }
                }
            }
        }
        check_win();
    }
    
    public void change_state(char ch, int m, int n)
    {
        switch(ch)
        {
            case 'c':
                alter(m,n);
                alter(m,Math.abs(n-1));
                alter(m,Math.abs(n-2));
                alter(Math.abs(m-1),n);
                alter(Math.abs(m-2),n);
                alter(Math.abs(m-1),Math.abs(n-1));
                break;
            case 'e':
                if(m==0 || m==3)
                {
                    alter(m,n-1);
                    alter(m,n+1);
                    alter(Math.abs(m-1),n);
                }
                else
                {
                    alter(m+1,n);
                    alter(m-1,n);
                    alter(m,Math.abs(n-1));
                }
                break;
            case 'i':
                alter(m,n);
                alter(m+1,n);
                alter(m-1,n);
                alter(m,n+1);
                alter(m,n-1);
                break;
            default:
                break;
        }
    }
    
    public void alter(int m , int n)
    {
        state[m][n]=!state[m][n];
        if(state[m][n])
        {
            table[m][n].setLabel(mark);
        }
        else
        {
            table[m][n].setLabel("");
        }
    }
    
    public void show_Hint()
    {
        int count=0;
        for(int x=0;x<row;x++)
        {
            for(int y=0;y<col;y++)
            {
                if(state[x][y])
                {
                    p[count]=1;
                }
                else
                {
                    p[count]=0;
                }
                count++;
            }
        }
        multiply();
        count=0; 
        int a=0;
        outer:
        for(int x=0;x<row;x++)
        {
            inner:
            for(int y=0;y<col;y++)
            {
                if(sol[count]==1)
                {
                    blink(x,y);
                    a++;
                }
                count++;
                if(a==hint_counter)
                {
                    break outer;
                }
            }
        }
    }
    
    public void blink(int m, int n)
    {
        try
        {
            table[m][n].setBackground(Color.blue);
            Thread.sleep(200);
            table[m][n].setBackground(Color.gray);
        }
        catch(InterruptedException e)
        {
            //necessary for thread
        }
    }
    
    public void multiply()
    {
        int sum=0;
        for(int x=0;x<16;x++)
        {
            sum=0;
            for(int y=0;y<16;y++)
            {
                sum=sum + (matrix[x][y]*p[y]);
            }
            sol[x]=sum%2;
        }
    }
    
    public void check_win()
    {
        boolean temp = true;
        outer:
        for(int x=0;x<row;x++)
        {
            inner:
            for(int y=0;y<col;y++)
            {
                if(state[x][y]==true)
                {
                    temp = false;
                    break outer;
                }
            }
        }
        if(temp)
        {
            char arr[]={'Y','O','U',' ','W','I','N'};
            int count = 0;
            Hint.setEnabled(false);
            for(int x=0;x<row;x++)
            {
                for(int y=0;y<col;y++)
                {
                    table[x][y].setEnabled(false);
                    if(count<7)
                    {
                        table[x][y].setBackground(Color.red);
                        table[x][y].setLabel(""+arr[count]);
                        count++;
                    }
                }
            }
        }
    }
    //These are not used but are necessary for mouseListener
    public void mouseEntered (MouseEvent e)
    {
    }

    public void mouseExited (MouseEvent e)
    {
    }

    public void mousePressed (MouseEvent e)
    {
    }

    public void mouseReleased (MouseEvent e)
    {
    }
    
}