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

        public void FULL(Component children, Component parent) {
            children.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        }

        public void PARENT_CHILD_HORIZONTAL(Component children, Component parent) {
            x = parent.getX() - children.getWidth();
            y = parent.getY();
            children.setBounds(x, y, children.getWidth(), children.getHeight());
        }

        public void PARENT_CHILD_VERTICAL(Component children, Component parent) {
            x = parent.getX();
            y = parent.getY() - children.getHeight();
            children.setBounds(x, y, children.getWidth(), children.getHeight());
        }

        public void PARENT_CHILD_VERTICAL(Component children, Component parent, int gap) {
            x = parent.getX();
            y = parent.getY() - children.getHeight() + gap;
            children.setBounds(x, y, children.getWidth(), children.getHeight());
        }

        public void PARENT_CHILD_VERTICAL_UNDER(Component children, Component parent, int gap) {
            x = parent.getX();
            y = parent.getY();
            children.setBounds(x, y, children.getWidth(), children.getHeight());
        }

        public void CUSTOM_WITH_PERCENT(Component component, int x, int y) {
            this.x = SCREEN_WIDTH * x / 100;
            this.y = SCREEN_HEIGHT * y / 100;
            component.setBounds(this.x, this.y, component.getWidth(), component.getHeight());
        }

        public void BOTTOM_CENTER_CHILD_PARENT(Component childern, Component parent) {
            x = parent.getWidth() / 2 - childern.getWidth() / 2;
            y = parent.getHeight() - childern.getHeight();
            childern.setBounds(x, y, childern.getWidth(), childern.getHeight());
        }

        public void CUSTOM_CHILD_PARENT(Component children, Component parent, int x, int y) {
            this.x = parent.getWidth() * x / 100;
            this.y = parent.getHeight() * y / 100;
            children.setBounds(this.x, this.y, children.getWidth(), children.getHeight());
        }

        public void CUSTOM_CHILD_PREFEREDSIZE_PARENT(Component children, Component parent, int x, int y) {
            this.x = (int) parent.getPreferredSize().getWidth() * x / 100;
            this.y = (int) parent.getPreferredSize().getHeight() * y / 100;
            children.setBounds(this.x, this.y, (int) children.getPreferredSize().getWidth(),
                    (int) children.getPreferredSize().getHeight());
        }

        public void CENTER_CUSTOM_Y_CHILDREN_PARENT_ARRAY_COMPONENT(Component[] childrens, Component parent, int gap,
                int y) {
            int int_lenghtAllChildrens = 0;
            for (Component c : childrens) {
                int_lenghtAllChildrens += c.getWidth();
            }

            int_lenghtAllChildrens += gap * (childrens.length - 1);

            this.x = parent.getWidth() / 2 - int_lenghtAllChildrens / 2;
            this.y = parent.getHeight() * y / 100;

            for (int i = 0; i < childrens.length; i++) {
                childrens[i].setBounds(this.x, this.y, childrens[i].getWidth(), childrens[i].getHeight());
                this.x += gap + childrens[i].getWidth();
            }
        }

        public void CENTER_CUSTOM_Y_CHILDREN_PARENT_ARRAY_COMPONENT(Component parent, int gap,
                int y, Component... childrens) {
            int int_lenghtAllChildrens = 0;
            for (Component c : childrens) {
                int_lenghtAllChildrens += c.getWidth();
            }

            int_lenghtAllChildrens += gap * (childrens.length - 1);

            this.x = parent.getWidth() / 2 - int_lenghtAllChildrens / 2;
            this.y = parent.getHeight() * y / 100;

            for (int i = 0; i < childrens.length; i++) {
                childrens[i].setBounds(this.x, this.y, childrens[i].getWidth(), childrens[i].getHeight());
                this.x += gap + childrens[i].getWidth();
            }
        }

        public void CENTER_CUSTOM_Y_CHILDREN_PARENT_ARRAY_COMPONENT(Component children, Component parent, int y) {

            this.x = parent.getWidth() / 2 - children.getWidth() / 2;
            this.y = parent.getHeight() * y / 100;

            children.setBounds(this.x, this.y, children.getWidth(), children.getHeight());
        }

        private int x;
        private int y;
    }

    private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    public final int SCREEN_WIDTH = (int) size.getWidth();
    public final int SCREEN_HEIGHT = (int) size.getHeight();
}
