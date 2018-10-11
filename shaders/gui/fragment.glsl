#version 400

in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D guiTexture;

uniform vec4 color;

void main(void){

	out_Color = texture(guiTexture,textureCoords);
	out_Color = vec4(color * out_Color);

}
