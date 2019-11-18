#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 texcoord;

uniform mat4 pr_matrix;

out DATA {
    vec2 texcoord;
} vs_out;

void main() {
    gl_Position = pr_matrix * position;
    vs_out.texcoord = texcoord;
}