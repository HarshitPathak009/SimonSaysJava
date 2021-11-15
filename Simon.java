import java.util.*; //Random
import javax.swing.*; // Jframe and JButton
import java.awt.*; //stores the grid layout
import java.awt.event.*; //ActionListener
public class Simon implements ActionListener
{
    int speed=1; 
    static int j=0, g=0;
    int c=0; 
    static boolean flg =false;
   JFrame f = new JFrame();
   JButton b[] = new JButton[9];
   JLabel score = new JLabel();
   JButton r = new JButton();
   JPanel p[] = new JPanel[3];
   String color [] = {"RED","GREEEN","YELLOW","BLUE"};
   static ArrayList<String> store = new ArrayList<String>();
   void restart()
   {
      g=0;
      score.setText("Score: "+g);
      for(int i=0;i<=3;i++)
      {
        b[i].addActionListener(this);
      }  
   }
   boolean check(String s)
   {
      if(s.equalsIgnoreCase(store.get(j)))
      {
          j++;
         return true;
       }
      else
          return false;
   }
   public static void main(String ar[])
   {
     Simon obj = new Simon();
     obj.setFrame();
     while(true)
     {
        if(flg==false){
          obj.game();
          flg=true;}
     }
   }
   public void game()
   {  
     if(flg == false)
     {
     Random r = new Random();
     store.add(color[r.nextInt(80000001)%4]);
     for(int i=0;i<store.size();i++)
     {
       for(int k=0;k<4;k++)
       {
          if(color[k].equalsIgnoreCase(store.get(i)))
          {
           switch(k){
            case 0:
             b[k].setBackground(Color.RED);
           break;
          case 1:
             b[k].setBackground(Color.GREEN);
             break;
          case 2:
             b[k].setBackground(Color.YELLOW);
             break;
         case 3:
             b[k].setBackground(Color.BLUE);
              break;
         }
          try
          {
            Thread.sleep(500/speed);
            b[k].setBackground(new JButton().getBackground());
            Thread.sleep(250);
           }
           catch(Exception e)
            {
              System.out.println("Sleep did not work");
            }
             break;
          }// if closed
       }//inner for loop closed
   }//outer for loop closed
  }
   }//method closed
   void setFrame()
   {
     f.setSize(900,900);
     f.setVisible(true); 
     f.setLayout(new BorderLayout());
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setButton();
     f.add(p[0],BorderLayout.NORTH);
     p[0].add(score);
     p[0].add(r);
     f.add(p[1],BorderLayout.CENTER);
     f.add(p[2],BorderLayout.SOUTH);
   }
   void setButton()
   {
     score.setFont(new Font("Arial",Font.PLAIN,36));
     score.setText("SCORE:"+c);
     score.setBackground(Color.BLACK);
    r.setActionCommand("RESTART");
    r.setFont(new Font("Arial",Font.PLAIN,36));
    r.setText("RESTART");
    r.addActionListener(this);
     p[0] = new JPanel();
     p[1] = new JPanel();
     p[2] = new JPanel();
     p[1].setLayout(new GridLayout(2,2));
      for(int i=0;i<=3;i++)
      {
        b[i] = new JButton();
        b[i].setActionCommand(color[i]);
        b[i].setFont(new Font("Arial",Font.PLAIN,36));
        b[i].setText(color[i]);
        b[i].addActionListener(this);
        p[1].add(b[i]);        
      }
   }
   void reset()//removes Action Listener from all buttons
   {
      g=0;
      score.setText("Score: "+g);
      store.clear();
      for(int i=0;i<=3;i++)
      {
        b[i].removeActionListener(this);
      }
   }
   public void actionPerformed(ActionEvent e)
   { 
      flg = true;
      c++;
      String s = e.getActionCommand();
      if(s.equalsIgnoreCase("Restart"))
      {
         restart();
         flg=false;
      }
      if(check(s)==false)
      {
        reset();
        c=0;
        g=0;
      }
      if(c==store.size())
      {
         c=0;
         j=0;
         g++;
         score.setText("Score: "+g);
            flg=false;
      }
   }
}