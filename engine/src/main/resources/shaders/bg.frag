#version 400

in vec2 pass_texcoord;
in vec3 pass_position;

out vec4 color;

uniform sampler2D textureSampler;
uniform float time;

void main() {
    float bonus = 1f;
    bonus = 1 + pass_position.y;
    color = texture(textureSampler, pass_texcoord) * (time * bonus) ;
}