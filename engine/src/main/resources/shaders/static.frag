#version 330

in vec4 color_pass;

layout(location = 0) out vec4 color;

void main() {
    color = color_pass;
}