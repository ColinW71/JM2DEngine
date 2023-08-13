package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectBoost extends Object {

    public ObjectBoost() {

        ID = "Boots";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("objects/boost.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
