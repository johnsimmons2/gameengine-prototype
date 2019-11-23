#version 400

in vec3 position;
in vec2 texcoord;

out vec2 pass_texcoord;
out vec3 pass_position;
uniform vec3 offset;

void main() {
    gl_Position = vec4(position + offset, 1.0);
    pass_texcoord = texcoord;
    pass_position = position;
}