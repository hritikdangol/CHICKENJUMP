import java.awt.*;
import javax.swing.*;
import src.Game;
import src.Path;

public class PanelGUI extends JPanel {

    private Game game;
    private Image chickenImg;
    private Image fireImg;

    public PanelGUI(Game game) {

        this.game = game;

        chickenImg = new ImageIcon("images/chicken.png").getImage();
        fireImg = new ImageIcon("images/fire.png").getImage();

        setBackground(new Color(135,206,235)); // Sky blue
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int startX = 50;
        int startY = 450;
        int pathWidth = 80;
        int pathHeight = 20;
        int gap = 25;

        // Draw all paths
        for (int i = 0; i < game.getPaths().size(); i++) {

            Path path = game.getPaths().get(i);

            int x = startX + i * (pathWidth + gap);

            // Draw path
            g.setColor(new Color(139,69,19));
            g.fillRect(x, startY, pathWidth, pathHeight);

            // Draw fire if this path has fire
            if (path.hasFire()) {
                g.drawImage(fireImg,
                        x + 20,
                        startY - 40,
                        40,
                        40,
                        this);
            }
        }

        // Draw chicken
        g.drawImage(chickenImg,(int) game.getChicken().getX(), (int) game.getChicken().getY(),50,50, this);
    }
}