package pixel;

public interface AnimationObserver
{
	public void onPlayedOnce (AnimatedSprite sprite);

	public static final AnimationObserver animatedSpriteResetter = new AnimationObserver ()
	{
		public void onPlayedOnce (AnimatedSprite sprite)
		{
			sprite.reset ();
		}
	};
}
