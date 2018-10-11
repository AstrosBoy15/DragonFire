package com.draglantix.engine;

import org.joml.Vector2f;

import com.draglantix.objects.Objects;

public class PhysicsEngine {
	
	public static void move(Vector2f dir, Objects obj) {
		obj.setPosition(obj.getPosition().add(dir));
	}
	
    public static void flip(Objects obj) {
    	float yRot;
    	
    	if(obj.getRotation().y == 180) {
    		yRot = 0;
    	}else {
    		yRot = 180;
    	}
    	
    	obj.setRotation(new Vector2f(obj.getRotation().x, yRot));
    }
    
    public static void rotate(Objects obj, float rot) {
    	obj.setRotation(new Vector2f(obj.getRotation().x + rot, obj.getRotation().y));
    }
}
