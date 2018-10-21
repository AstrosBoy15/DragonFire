#version 400

in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D entityTexture;

uniform bool colorType;
uniform bool usesTex;

uniform vec4 c0;
uniform vec4 c1;
uniform vec4 c2;
uniform vec4 c3;
uniform vec4 c4;

uniform vec4 color;

void main(void) {
	if (usesTex) {
		out_Color = texture(entityTexture, textureCoords);
	} else {
		out_Color = vec4(1, 1, 1, 1);
	}

	float scaleColor = out_Color.x * 255;

	if (colorType == true) {
		out_Color = vec4(color * out_Color);
	} else {
		if (scaleColor < 51) {
			out_Color = vec4(c0 * out_Color);
		} else if (scaleColor >= 51 && scaleColor < 102) {
			out_Color = vec4(c1 * out_Color);
		} else if (scaleColor >= 102 && scaleColor < 153) {
			out_Color = vec4(c2 * out_Color);
		} else if (scaleColor >= 153 && scaleColor < 204) {
			out_Color = vec4(c3 * out_Color);
		} else {
			out_Color = vec4(c4 * out_Color);
		}
	}
}
