package com.sebgghb22.android.game;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;

public final class MainScene extends CCLayer {
    public static CCScene scene(Game g)  {
        /*
         * Create the scene for this layer
         */
        CCScene scene = CCScene.node();

        scene.addChild(new MainGame(g));

        return scene;
    }
}
