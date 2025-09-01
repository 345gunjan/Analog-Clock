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

public class Psdude extends JPanel implements Runnable {
    Thread thread;
    int i, j, k, p = 0, q = 0;
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
        super.paint(g); // important to clear previous drawings!
        clockLayout(g);

        cd = new Date();
        sdf.applyPattern("s");
        i = Integer.parseInt(sdf.format(cd));
        sdf.applyPattern("m");
        j = Integer.parseInt(sdf.format(cd));
        sdf.applyPattern("h");
        k = Integer.parseInt(sdf.format(cd));

        // Coordinate arrays for hand calculations
        int[] str = {0, 12, 24, 37, 48, 60, 70, 80, 89, 97, 103, 109, 114, 117, 119, 119};
        int[] str1 = {0, 10, 20, 30, 40, 50, 58, 66, 74, 80, 86, 91, 95, 97, 99, 100};
        int[] str2 = {0, 40, 69, 80};
        int[] str3 = {9, 48, 73, 79, 63, 31};
        int[] str4 = {20, 56, 77};
        int[] str5 = {29, 63, 79, 74, 49, 11};
        int m = str.length;
        int n = str1.length;

        // Second hand coordinates
        if (i < 16) {
            xsec = str[i] + xOrigin;
            ysec = -str[n - 1 - i] + yOrigin;
        } else if (i <= 30) {
            xsec = str[m - 1 - i + 15] + xOrigin;
            ysec = str[i - 15] + yOrigin;
        } else if (i <= 45) {
            xsec = -str[i - 30] + xOrigin;
            ysec = str[m - 1 - i + 30] + yOrigin;
        } else if (i < 60) {
            xsec = -str[m - 1 - i + 45] + xOrigin;
            ysec = -str[i - 45] + yOrigin;
            if (i == 59) {
                i = i - 60;
                j++;
            }
        }
        i++;

        // Draw Second hand
        g.setColor(Color.cyan);
        g.drawLine(xOrigin, yOrigin, xsec, ysec);

        // Minute hand coordinates
        if (j < 16) {
            xmin = str1[j] + xOrigin;
            ymin = -str1[n - 1 - j] + yOrigin;
        } else if (j <= 30) {
            xmin = str1[n - 1 - j + 15] + xOrigin;
            ymin = str1[j - 15] + yOrigin;
        } else if (j <= 45) {
            xmin = -str1[j - 30] + xOrigin;
            ymin = str1[n - 1 - j + 30] + yOrigin;
        } else if (j <= 60) {
            xmin = -str1[n - 1 - j + 45] + xOrigin;
            ymin = -str1[j - 45] + yOrigin;
            if (j == 60) {
                j = j - 60;
                k++;
            }
        }

        // Draw Minute hand
        g.setColor(Color.magenta);
        g.drawLine(xOrigin, yOrigin, xmin, ymin);

        // Hour hand coordinates (approximate)
        if (k == 0) k = 12; // Treat midnight as 12

        if (k == 1) {
            if (j <= 15) {
                p = str2[1]; q = str2[2];
            } else if (j <= 30) {
                p = str3[1]; q = str3[4];
            } else if (j <= 45) {
                p = str4[1]; q = str4[1];
            } else if (j < 60) {
                p = str5[1]; q = str[4];
            }
            xhr = p + xOrigin;
            yhr = -q + yOrigin;
        } else if (k == 2) {
            if (j <= 15) {
                p = str2[2]; q = str2[1];
            } else if (j <= 30) {
                p = str3[2]; q = str3[5];
            } else if (j <= 45) {
                p = str4[2]; q = str4[0];
            } else if (j < 60) {
                p = str5[2]; q = str5[5];
            }
            xhr = p + xOrigin;
            yhr = -q + yOrigin;
        } else if (k == 3) {
            if (j <= 15) {
                p = str2[3]; q = str2[0];
            } else if (j <= 30) {
                p = str3[3]; q = str3[0];
            } else if (j <= 45) {
                p = str4[2]; q = str4[0];
            } else if (j < 60) {
                p = str5[3]; q = str5[0];
            }
            xhr = p + xOrigin;
            yhr = q + yOrigin;
        } else if (k == 4) {
            if (j <= 15) {
                p = str2[2]; q = str2[1];
            } else if (j <= 30) {
                p = str3[4]; q = str3[1];
            } else if (j <= 45) {
                p = str4[1]; q = str4[1];
            } else if (j < 60) {
                p = str5[4]; q = str5[1];
            }
            xhr = p + xOrigin;
            yhr = q + yOrigin;
        } else if (k == 5) {
            if (j <= 15) {
                p = str2[1]; q = str2[2];
            } else if (j <= 30) {
                p = str3[5]; q = str3[2];
            } else if (j <= 45) {
                p = str4[0]; q = str4[2];
            } else if (j < 60) {
                p = str5[5]; q = str5[2];
            }
            xhr = p + xOrigin;
            yhr = q + yOrigin;
        } else if (k == 6) {
            if (j <= 15) {
                p = str2[0]; q = str2[3];
            } else if (j <= 30) {
                p = str3[0]; q = str3[3];
            } else if (j <= 45) {
                p = str4[0]; q = str4[2];
            } else if (j < 60) {
                p = str5[0]; q = str5[3];
            }
            xhr = -p + xOrigin;
            yhr = q + yOrigin;
        } else if (k == 7) {
            if (j <= 15) {
                p = str2[1]; q = str2[2];
            } else if (j <= 30) {
                p = str3[1]; q = str3[4];
            } else if (j <= 45) {
                p = str4[1]; q = str4[1];
            } else if (j < 60) {
                p = str5[1]; q = str5[4];
            }
            xhr = -p + xOrigin;
            yhr = q + yOrigin;
        }

        // Draw Hour hand
        g.setColor(Color.orange);
        g.drawLine(xOrigin, yOrigin, xhr, yhr);
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000); // update every second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Analog & Digital Clock with Calendar");
        Psdude1 panel = new Psdude1();
        frame.add(panel);
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
