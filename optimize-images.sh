#!/bin/bash

INPUT_DIR="src/main/resources/static/EivindsVakreØyeblikkOld"
OUTPUT_DIR="src/main/resources/static/EivindsVakreØyeblikk"

mkdir -p "$OUTPUT_DIR"

for file in "$INPUT_DIR"/*.{jpg,jpeg,png,JPG,JPEG,PNG}; do
  if [ -f "$file" ]; then
    filename=$(basename "$file")
    name="${filename%.*}"
    cwebp -q 80 "$file" -o "$OUTPUT_DIR/$name.webp"
    echo "✅ Optimalisert: $filename"
  fi
done

echo "🎉 Ferdig! Optimaliserte bilder ligger i $OUTPUT_DIR"
