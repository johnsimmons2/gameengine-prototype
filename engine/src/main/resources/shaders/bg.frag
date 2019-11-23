#version 400

in vec2 pass_texcoord;

out vec4 color;

uniform sampler2D textureSampler;

void main() {
    color = texture(textureSampler, pass_texcoord);
}