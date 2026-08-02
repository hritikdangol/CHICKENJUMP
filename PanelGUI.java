import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import src.Game;
import src.Path;
import javax.sound.sampled.*;//sound ko lagi
import java.io.File;

public class PanelGUI extends JPanel {
    private Game game;
    private FrameGUI frame;

    private Image chickenImg;
    private Image fireImg;

    public PanelGUI(Game game, FrameGUI frame) {

        this.game = game;
        this.frame = frame;
        chickenImg = new ImageIcon("assets/chicken.png").getImage();
        fireImg = new ImageIcon("assets/fire.png").getImage();
        playBackgroundMusic();

        // Background color
        setBackground(new Color(135, 206, 235));

        // keyboard inout ko lagi
        setFocusable(true);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_SPACE) { // spacebar====jump ko lagi

                    if (!game.isStarted()) {
                        return;
                    }
                    game.jump();

                    frame.updateMultiplier();
                    frame.updateBalance();
                    repaint(); // game sakesi feri suru garna lai
                    if (game.isGameOver()) {
                        playSound("assets/deadchickesound.wav");
                        JOptionPane.showMessageDialog(frame, "Game Over!");

                        // Reset chicken only
                        game.getChicken().setX(50);
                        game.getChicken().setY(395);
                        game.resetDisplay();
                        frame.updateBalance();
                        frame.updateMultiplier();
                        frame.resetBetUI();

                        repaint();
                    }
                }
            }
        });
    }

    private void playSound(String fileName) {
        try {
            File file = new File(fileName);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

            Clip clip = AudioSystem.getClip();

            clip.open(audioStream);

            System.out.println("Playing: " + fileName);

            clip.start();

        } catch (Exception e) {
            e.printStackTrace(); // IMPORTANT
        }
    }

    private void playBackgroundMusic() {

        try {

            System.out.println("Background music started");

            File file = new File("assets/bgsound.wav");

            AudioInputStream audio = AudioSystem.getAudioInputStream(file);

            Clip clip = AudioSystem.getClip();

            clip.open(audio);

            clip.loop(Clip.LOOP_CONTINUOUSLY);

            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int startX = 70;
        int startY = 460;
        int pathWidth = 100;
        int pathHeight = 20;
        int gap = 30;

        for (int i = 0; i < game.getPaths().size(); i++) {
            Path path = game.getPaths().get(i);
            int x = startX + i * (pathWidth + gap);
            // Draw path
            g.setColor(new Color(139, 69, 19));
            g.fillRect(x, startY, pathWidth, pathHeight);

            // Show fire only on the when the player hit
            if (game.isGameOver() && i == game.getFirePathIndex()) {

           g.drawImage(fireImg, x - 10, startY - 120, 170, 140, this);
            }
        }
        g.drawImage(
                chickenImg, (int) game.getChicken().getX(), (int) game.getChicken().getY(), 150, 65, this);
    }

}