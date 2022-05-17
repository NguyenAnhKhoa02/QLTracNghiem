package GUI.CommonClasses;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;

public class ParameterScreen {
    public ParameterScreen() {

    }

    public class Position {
        public Position() {

        }

        public void CENTER(Component component) {
            x = SCREEN_WIDTH / 2 - component.getWidth() / 2;
            y = SCREEN_HEIGHT / 2 - component.getHeight() / 2;
            component.setBounds(x, y, component.getWidth(), component.getHeight());
        }

        public void BOTTOM_CENTER(Component component) {
            x = SCREEN_WIDTH / 2 - component.getWidth() / 2;
            y = SCREEN_HEIGHT - component.getHeight();
            component.setBounds(x, y, component.getWidth(), component.getHeight());
        }

        public void TOP_CENTER(Component component) {
            x = SCREEN_WIDTH / 2 - component.getWidth() / 2;
            y = 0;
            component.setBounds(x, y, component.getWidth(), component.getHeight());
        }

        private int x;
        private int y;
    }

    private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    public final int SCREEN_WIDTH = (int) size.getWidth();
    public final int SCREEN_HEIGHT = (int) size.getHeight();
}
