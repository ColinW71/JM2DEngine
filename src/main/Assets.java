package main;

import object.ObjectBoost;

public class Assets {
    Panel panel;
    public Assets(Panel panel) {
        this.panel = panel;

    }
    public void setObject() {
        panel.obj[1] = new ObjectBoost();
        panel.obj[1].worldX = 39 * panel.tileSize;
        panel.obj[1].worldY = 10 * panel.tileSize;
    }
}
