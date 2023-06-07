import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class animeTemplate extends JFrame implements ActionListener {
    private JButton btnBack, btnCharacter, btnNew, btnDelete;
    private LabelComponent labelComponent;

    public animeTemplate() {
        // Create buttons
        Dimension buttonSize = new Dimension(100, 30);
        btnBack = new JButton("Back");
        btnBack.setPreferredSize(buttonSize);
        btnBack.addActionListener(this);

        btnCharacter = new JButton("Character");
        btnCharacter.setPreferredSize(buttonSize);
        btnCharacter.addActionListener(this);

        btnNew = new JButton("New");
        btnNew.setPreferredSize(buttonSize);
        btnNew.addActionListener(this);

        btnDelete = new JButton("Delete");
        btnDelete.setPreferredSize(buttonSize);
        btnDelete.addActionListener(this);

        // Create the label component
        labelComponent = new LabelComponent();

        // A Nested layout (it means a layout inside a layout)
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setOpaque(false);
        btnPanel.add(btnBack);
        btnPanel.add(btnCharacter);
        btnPanel.add(btnNew);
        btnPanel.add(btnDelete);

        // Add btnPanel to buttonPanel
        buttonPanel.add(btnPanel, BorderLayout.CENTER);

        JPanel picturesPanel = new JPanel(new BorderLayout()) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Toolkit tk = Toolkit.getDefaultToolkit();
                Image backgroundImg = tk.getImage("piece.jpg");
                g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);

                Image poster = tk.getImage("one.jpg");
                if (poster != null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    g2d.drawImage(poster, getWidth() - 690, 20, 200, 300, this);
                    g2d.dispose();
                }

                // Draw the labels
                labelComponent.paintComponent(g);
            }//end paint
        };

        picturesPanel.add(buttonPanel, BorderLayout.SOUTH);
        picturesPanel.add(labelComponent, BorderLayout.CENTER);

        setContentPane(picturesPanel);

        setTitle("Animes Archives");
        setSize(720, 405);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            //Not done
            System.out.println("Back testing");
        } else if (e.getSource() == btnCharacter) {
            //Not done
            System.out.println("Character testing");
        } else if (e.getSource() == btnNew) {
            //Not done
            System.out.println("New testing");
        } else if (e.getSource() == btnDelete) {
            //Not done
            System.out.println("Delete testing");
        }
    }

    //The main() method 
    public static void main(String[] args) {
        animeTemplate app = new animeTemplate();
    }

    private static class LabelComponent extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the labels at specific positions
            //Title
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString("One Piece", 230, 35);

            //Description
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString("Description: Monkey D. Luffy and his pirate crew in order to find the greatest", 230, 55);
            g.drawString("treasure ever left by the legendary Pirate, Gold Roger, the famous mystery", 230, 75);
            g.drawString("treasure named One Piece.", 230, 95);
        }
    }
}
