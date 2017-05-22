package com.mygdx.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MegaSoldier;
import com.mygdx.game.Sprites.Brick;
import com.mygdx.game.Sprites.Objeto;

/**
 * Created by Karla Rosas on 22/05/2017.
 */

public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap map)
    {
        //cambiar a cada clase
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //Crear fixtures del suelo
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rec = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rec.getX() + rec.getWidth()/2) / MegaSoldier.PPM, (rec.getY() + rec.getHeight()/2)/MegaSoldier.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rec.getWidth()/2) / MegaSoldier.PPM, (rec.getHeight()/2)/MegaSoldier.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);

        }

        //Crear fixtures para tuberia
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rec = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rec.getX() + rec.getWidth()/2) /MegaSoldier.PPM, (rec.getY() + rec.getHeight()/2)/MegaSoldier.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rec.getWidth()/2) / MegaSoldier.PPM, (rec.getHeight()/2)/MegaSoldier.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);

        }
        //Ladrillos
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rec = ((RectangleMapObject) object).getRectangle();

            new Brick(world, map, rec);
        }
        //Objetos
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rec = ((RectangleMapObject) object).getRectangle();

            new Objeto(world, map, rec);

        }
    }
}
