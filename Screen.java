import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.List;

public class Screen {
    private final int width = 1000;
    private final int height = 800;
    private final String name = "Jaikin";
    private final Frame window;
    private final Canvas canvas;
    private final Manager manager;

    public Screen(Manager manager) {
        Frame frame = new Frame(name);
        frame.setSize(width, height);
        frame.setResizable(false);

        this.manager = manager;
        this.window = frame;
        this.canvas = new Canvas();
    
        this.window.add(this.canvas);

        addKeyListner();
        addMouseListner();
        addWindowListener();

        this.window.setVisible(true);
        this.canvas.requestFocusInWindow();
        this.canvas.createBufferStrategy(2);
    }

    public void render(){
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) return;   

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Point p : manager.getAllPoints()) {
            drawCircle(g, (int) p.getX(), (int) p.getY());
        }

        if (manager.isAnimating()) {
            List<Point> points = manager.getChakinPoints();
            for (int i = 0; i < points.size() - 1; i++){
                Point p1 = points.get(i);
                Point p2 = points.get(i + 1);
                drawLine(g, (int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
            }
        }

        g.setColor(Color.LIGHT_GRAY);
        g.drawString(manager.getStatusMessage(), 12, 20);
        g.drawString("C: clear    Esc: exit", 12, 40);

        g.dispose();
        bs.show();
    }

    private void drawCircle(Graphics g,int x, int y){
        int radius = 2;
        g.setColor(Color.WHITE);
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    private void drawLine(Graphics g,int x1, int y1, int x2, int y2){
        g.setColor(Color.WHITE);
        g.drawLine(x1, y1, x2, y2);
    }
    
    private void addWindowListener() {
        this.window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void addKeyListner(){
        this.canvas.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE :
                        System.exit(0);
                        break;
                    case KeyEvent.VK_ENTER :
                        manager.startAnimation();
                        break;
                    case KeyEvent.VK_C :
                        manager.removeAllPoints();
                        break;
                }
            }
        });
    }

    private void addMouseListner(){
        this.canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){

                if (e.getButton() != MouseEvent.BUTTON1) {
                    return;
                }

                int x = e.getX();
                int y = e.getY();
                manager.addPoint(x, y);
            }
        });
    }
}