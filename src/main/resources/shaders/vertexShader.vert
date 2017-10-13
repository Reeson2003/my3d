#version 400 core

in vec3 position;
in vec2 textureCoordinates;

out vec2 pass_textureCoordinates;
out vec3 colour;

uniform mat4 transformationMatrix;

void main(void) {
    gl_Position = vec4(position, 1.0);
    pass_textureCoordinates = textureCoordinates;
    colour = vec3(position.x + 0.5, 0.0, position.y + 0.5);
}