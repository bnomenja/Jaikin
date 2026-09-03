import java.awt.*;
import java.awt.event.*;

public class Screen {
    private final int width = 800;
    private final int height = 600;
    private final String name = "Jaikin";
    private Frame window;
    private final Manager manager;

    public Screen(Manager manager) {
        Frame frame = new Frame(name);
        frame.setSize(width, height);
        // frame.setResizable(false);
        frame.setBackground(Color.BLACK);

        this.manager = manager;
        this.window = frame;

        addKeyListner();
        addMouseListner();
    }

    public void display() {
        this.window.setVisible(true);
    }

    private void drawCircle(int x, int y){
    }

    private void closeWindow(){
    }

    private void clearScreen(){
    }

    private void startAnimation(){
    }

    private void stopAnimation(){
    }

    private void addKeyListner(){
    }

    private void addMouseListner(){
        window.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e){
                int x = e.getX();
                int y = e.getY();

                manager.addPoint(x, y);
                drawCircle(x, y);
            }

            @Override
            public void mousePressed(MouseEvent e){}
            @Override
            public void mouseEntered(MouseEvent e){}
            @Override
            public void mouseExited(MouseEvent e){}
            @Override
            public void mouseReleased(MouseEvent e){}
        });
    }
}