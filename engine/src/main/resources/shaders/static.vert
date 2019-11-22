#version 330

layout(location = 0) in vec3 position;

out vec4 color_pass;

void main() {
    gl_Position = vec4(position, 1.0f);
    color_pass = vec4(position, 0.5f);
}