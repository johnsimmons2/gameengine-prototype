#version 330 core

layout (location = 0) out vec4 color;

in DATA {
    vec2 texcoord;
} fs_in;

uniform sampler2D tex;

void main() {
    color = texture(tex, fs_in.texcoord);
}