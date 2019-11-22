#version 400

in vec2 pass_texcoord;

out vec4 color;

uniform sampler2D textureSampler;

void main() {
    vec2 test = vec2(1, 1);
    color = vec4(1, 1, 1, 0);
    //color = texture(textureSampler, pass_texcoord);
}