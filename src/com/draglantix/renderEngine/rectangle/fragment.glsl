#version 400

in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D entityTexture;

uniform bool colorType;

uniform vec4 c0;
uniform vec4 c1;
uniform vec4 c2;
uniform vec4 c3;
uniform vec4 c4;

uniform vec4 color;

void main(void){

	out_Color = texture(entityTexture,textureCoords);

	if(colorType == true){
		out_Color = vec4(color * out_Color);
	}else{
		if(out_Color.x == 0){
			out_Color = vec4(c0.xyz, out_Color.w);
		}else if(out_Color.x > 0 && out_Color.x <= 0.25){
			out_Color = vec4(c1.xyz, out_Color.w);
		}else if(out_Color.x > 0.25 && out_Color.x <= 0.5){
			out_Color = vec4(c2.xyz, out_Color.w);
		}else if(out_Color.x > 0.5 && out_Color.x <= 0.99){
			out_Color = vec4(c3.xyz, out_Color.w);
		}else{
			out_Color = vec4(c4.xyz, out_Color.w);
		}
	}
}
