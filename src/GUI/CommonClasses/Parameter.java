package GUI.CommonClasses;

import java.awt.Dimension;
import java.awt.Toolkit;

import GUI.CommonClasses.ParameterScreen.Position;

public interface Parameter {
    ParameterScreen parameterScreen = new ParameterScreen();
    ParameterScreen.Position posInScreen = parameterScreen.new Position();

    public void parameter();
}
