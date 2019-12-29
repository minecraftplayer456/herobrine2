in_lines = open('graveyard_world.yml').read().splitlines()
out_lines = []

for line in in_lines:
	if line[:6] == "    Y:":
		parts = line.split(': ')
		parts[1] = str(int(parts[1]) + 2)
		out_lines.append(': '.join(parts) + '\n')
	else:
		out_lines.append(line + '\n')
		
out_file = open('graveyard_world_new.yml', 'w')
out_file.writelines(out_lines)