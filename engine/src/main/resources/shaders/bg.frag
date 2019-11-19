#version 330 core

in vec2 pass_texcoord;

layout (location = 0) out vec4 color;

uniform sampler2D tex;

void main() {
    color = texture(tex, pass_texcoord);
}