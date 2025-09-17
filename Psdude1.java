import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Psdude1 extends JPanel implements Runnable {
    Thread thread;
    int i, j, k;
    String year, month, day, second, minute, hour;
    SimpleDateFormat sdf = new SimpleDateFormat("s", Locale.getDefault());
    Date cd;
    int xOrigin = 375, yOrigin = 375, xsec, ysec, xmin, ymin, xhr, yhr;

    private void clockLayout(Graphics g) {
        Calendar calendar = new GregorianCalendar();
        year = String.valueOf(calendar.get(Calendar.YEAR));
        month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        day = String.valueOf(calendar.get(Calendar.DATE));
        second = String.valueOf(calendar.get(Calendar.SECOND));
        minute = String.valueOf(calendar.get(Calendar.MINUTE));
        hour = String.valueOf(calendar.get(Calendar.HOUR) == 0 ? 12 : calendar.get(Calendar.HOUR)); // 12-hour format

        g.setColor(Color.black);
        g.fillRoundRect(590, 200, 340, 370, 50, 50);
        g.setColor(Color.pink);
        g.fillRoundRect(600, 210, 320, 350, 50, 50);

        g.setColor(Color.black);
        g.fillRoundRect(620, 280, 80, 50, 30, 30);
        g.fillRoundRect(710, 280, 80, 50, 30, 30);
        g.fillRoundRect(800, 280, 100, 50, 30, 30);
        g.fillRoundRect(620, 350, 80, 50, 30, 30);
        g.fillRoundRect(710, 350, 80, 50, 30, 30);
        g.fillRoundRect(800, 350, 100, 50, 30, 30);
        g.fillRoundRect(630, 470, 80, 50, 30, 30);
        g.fillRoundRect(720, 470, 80, 50, 30, 30);
        g.fillRoundRect(810, 470, 80, 50, 30, 30);

        g.setColor(Color.green);
        g.fillRoundRect(610, 220, 300, 50, 30, 30);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g.drawString("CALENDAR", 675, 255);

        g.setColor(Color.green);
        g.fillRoundRect(610, 415, 300, 50, 30, 30);
        g.setColor(Color.black);
        g.drawString("DIGITAL CLOCK", 630, 450);

        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g.drawString(" DD ", 630, 310);
        g.drawString(" MM ", 715, 310);
        g.drawString("YYYY", 810, 310);

        g.drawString(day, 650, 385);
        g.drawString(month, 740, 385);
        g.drawString(year, 820, 385);

        g.drawString(hour + "H", 645, 505);
        g.drawString(minute + " M", 725, 505);
        g.drawString(second + " S", 825, 505);

        // Hanger Layout
        g.setColor(Color.black);
        g.fillOval(749, 154, 10, 10);
        g.fillOval(698, 213, 10, 10);
        g.fillOval(808, 213, 10, 10);
        g.drawLine(753, 160, 700, 220);
        g.drawLine(754, 161, 701, 221);
        g.drawLine(753, 160, 815, 220);
        g.drawLine(752, 159, 814, 219);

        // Clock Circles
        g.setColor(Color.blue);
        g.fillOval(xOrigin - 160, yOrigin - 160, 320, 320);
        g.setFont(new Font("TimesRoman", Font.BOLD, 25));
        g.setColor(Color.black);
        g.fillOval(xOrigin - 150, yOrigin - 150, 300, 300);

        // Clock Midpoint
        g.setColor(Color.green);
        g.fillOval(xOrigin - 5, yOrigin - 5, 10, 10);

        // Analog text Format
        g.setColor(Color.black);
        g.fillRoundRect(225, 540, 300, 50, 30, 30);
        g.setColor(Color.white); 
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g.drawString("ANALOG CLOCK", 245, 575);


        // String Written Text
        g.setColor(Color.blue);
        g.drawString("Clock Design By Gunjan Soni", 300, 100);
        g.drawString("Under the Guidance of", 180, 150);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.BOLD, 35));
        g.drawString("Mr. Manish Bhatia Sir", 480, 150);

        // Clock Label
        g.setColor(Color.red);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("12", xOrigin - 10, yOrigin - 130);
        g.drawString("1", xOrigin + 70, yOrigin - 115);
        g.drawString("2", xOrigin + 119, yOrigin - 64);
        g.drawString("3", xOrigin + 135, yOrigin + 5);
        g.drawString("4", xOrigin + 120, yOrigin + 74);
        g.drawString("5", xOrigin + 65, yOrigin + 125);
        g.drawString("6", xOrigin - 5, yOrigin + 145);
        g.drawString("7", xOrigin - 70, yOrigin + 125);
        g.drawString("8", xOrigin - 125, yOrigin + 80);
        g.drawString("9", xOrigin - 145, yOrigin + 0);
        g.drawString("10", xOrigin - 125, yOrigin - 65);
        g.drawString("11", xOrigin - 80, yOrigin - 110);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // clear previous drawings
        setBackground(Color.lightGray);
        clockLayout(g);

        cd = new Date();
        sdf.applyPattern("s");
        i = Integer.parseInt(sdf.format(cd));
        sdf.applyPattern("m");
        j = Integer.parseInt(sdf.format(cd));
        sdf.applyPattern("h");
        k = Integer.parseInt(sdf.format(cd));

        // ---- FIXED HAND DRAWING ----

        // Second hand
        double secAngle = Math.toRadians(6 * i - 90);
        xsec = (int) (xOrigin + Math.cos(secAngle) * 140);
        ysec = (int) (yOrigin + Math.sin(secAngle) * 140);
        g.setColor(Color.cyan);
        g.drawLine(xOrigin, yOrigin, xsec, ysec);

        // Minute hand
        double minAngle = Math.toRadians(6 * j - 90);
        xmin = (int) (xOrigin + Math.cos(minAngle) * 120);
        ymin = (int) (yOrigin + Math.sin(minAngle) * 120);
        g.setColor(Color.magenta);
        g.drawLine(xOrigin, yOrigin, xmin, ymin);

        // Hour hand
        double hourAngle = Math.toRadians((30 * k + j / 2) - 90);
        xhr = (int) (xOrigin + Math.cos(hourAngle) * 90);
        yhr = (int) (yOrigin + Math.sin(hourAngle) * 90);
        g.setColor(Color.orange);
        g.drawLine(xOrigin, yOrigin, xhr, yhr);
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000);  // Refresh every second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Analog Digital Clock");
        Psdude1 clock = new Psdude1();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.add(clock);
        frame.setVisible(true);
        clock.thread = new Thread(clock);
        clock.thread.start();
    }
}
