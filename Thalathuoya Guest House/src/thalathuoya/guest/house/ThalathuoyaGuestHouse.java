package thalathuoya.guest.house;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ThalathuoyaGuestHouse extends JFrame implements ActionListener {
    JLabel text;
    Timer timer;
    float opacity = 0.0f;

    ThalathuoyaGuestHouse() {
       
        
        setBounds(100, 100, 1080, 566);
        setTitle("Thalathuoya Guest House");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        JPanel background = new JPanel() {
            private Image backgroundImage = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        add(background);

        
      text = new JLabel("Thalathuoya Guest House") {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE); 
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(getText());
        int height = fm.getHeight();
        
        
        g2d.setColor(new Color(0, 144, 200));
        g2d.drawString(getText(), (getWidth() - width) / 2 - 1, ((getHeight() - height) / 2) + fm.getAscent() - 1); 
        g2d.drawString(getText(), (getWidth() - width) / 2 + 1, ((getHeight() - height) / 2) + fm.getAscent() - 1); 
        g2d.drawString(getText(), (getWidth() - width) / 2 + 1, ((getHeight() - height) / 2) + fm.getAscent() + 1); 
        g2d.drawString(getText(), (getWidth() - width) / 2 - 1, ((getHeight() - height) / 2) + fm.getAscent() + 1); 
        
        
        g2d.setColor(Color.WHITE); 
        g2d.drawString(getText(), (getWidth() - width) / 2, ((getHeight() - height) / 2) + fm.getAscent());
        
        g2d.dispose();
    }
};
       
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setFont(new Font("Arial", Font.BOLD, 50));
        text.setForeground(Color.WHITE);
        text.setOpaque(false);
        background.add(Box.createVerticalStrut(200));
        background.add(text);

        
        JButton next = new JButton("Next") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(new Color(bg.getRed(), bg.getGreen(), bg.getBlue(), 200));
            }
        };
        next.setAlignmentX(Component.CENTER_ALIGNMENT);
        next.setBackground(new Color(0, 123, 255)); 
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Arial", Font.PLAIN, 24));
        next.setFocusPainted(false);
        next.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        next.setCursor(new Cursor(Cursor.HAND_CURSOR));
        next.addActionListener(this);

        
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                next.setBackground(new Color(0, 104, 217));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                next.setBackground(new Color(0, 123, 255));
            }
        });

        background.add(Box.createVerticalStrut(20));
        background.add(next);

        
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (opacity < 1.0f) {
                    opacity = Math.min(1.0f, opacity + 0.1f);
                    text.repaint();
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login();
        if (timer != null) {
            timer.stop();
        }
    }

    public static void main(String[] args) {
        new ThalathuoyaGuestHouse();
    }
}
