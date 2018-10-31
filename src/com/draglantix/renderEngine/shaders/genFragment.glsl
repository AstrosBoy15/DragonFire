#version 400

in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D entityTexture;

uniform bool usesTex;

uniform vec4 colors[20];

uniform int colorsLength;

void main(void) {
	if (usesTex) {
		out_Color = texture(entityTexture, textureCoords);
	} else {
		out_Color = vec4(1, 1, 1, 1);
	}

	float scaleColor = out_Color.x * 255;
	float bounds = 255 / colorsLength;

	for (int i = 0; i < colorsLength; i++) {
		if (scaleColor >= bounds * i && scaleColor <= bounds * (i + 1)) {
			out_Color = vec4(colors[i] * out_Color);
		}
	}

}
