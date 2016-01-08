package cam.graphics;

public class Sprite {

	public final int SIZE;
	public int x, y;
	public SpriteSheet sheet;

	public int[] pixels;
	
	public static Sprite voidSprite = new Sprite(16, 0xff00246B);
	public static Sprite test = new Sprite(16, 0xffffffff);
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.main);
	public static Sprite sandstone = new Sprite(16, 1, 0, SpriteSheet.main);
	public static Sprite wood = new Sprite(16, 2, 0, SpriteSheet.main);
	public static Sprite water = new Sprite(16, 3, 0, SpriteSheet.main);
	public static Sprite sand = new Sprite(16, 4, 0, SpriteSheet.main);
	public static Sprite darkWood = new Sprite(16, 5, 0, SpriteSheet.main);
	public static Sprite glass = new Sprite(16, 6, 0, SpriteSheet.main);
	
	public static Sprite man_down = new Sprite(32, 4, 0, SpriteSheet.characters);
	public static Sprite man_up = new Sprite(32, 7, 0, SpriteSheet.characters);
	public static Sprite man_left = new Sprite(32, 6, 0, SpriteSheet.characters);
	public static Sprite man_right = new Sprite(32, 5, 0, SpriteSheet.characters);
	public static Sprite oldman_down = new Sprite(32, 4, 1, SpriteSheet.characters);
	public static Sprite oldman_up = new Sprite(32, 7, 1, SpriteSheet.characters);
	public static Sprite oldman_left = new Sprite(32, 6, 1, SpriteSheet.characters);
	public static Sprite oldman_right = new Sprite(32, 5, 1, SpriteSheet.characters);
	public static Sprite woman_down = new Sprite(32, 0, 0, SpriteSheet.characters);
	public static Sprite woman_up = new Sprite(32, 3, 0, SpriteSheet.characters);
	public static Sprite woman_left = new Sprite(32, 2, 0, SpriteSheet.characters);
	public static Sprite woman_right = new Sprite(32, 1, 0, SpriteSheet.characters);
	public static Sprite player_up = new Sprite(32, 2, 1, SpriteSheet.characters);
	public static Sprite player_down = new Sprite(32, 0, 1, SpriteSheet.characters);
	public static Sprite player_left = new Sprite(32, 3, 1, SpriteSheet.characters);
	public static Sprite player_right = new Sprite(32, 1, 1, SpriteSheet.characters);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.SIZE = size;
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		pixels = new int[size * size];
		load();

	}

	public Sprite(int size, int color) {
		this.SIZE = size;
		pixels = new int[size * size];
		setColor(color);
	}
	
	private void setColor(int color){
		for(int i = 0; i < SIZE*SIZE; i++){
			pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}
