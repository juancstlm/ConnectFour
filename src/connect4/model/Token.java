package connect4.model;

public class Token{
	private Player player;
	private Token tc;
	private Token tl;
	private Token tr;
	private Token ml;
	private Token mr;
	private Token bl;
	private Token bc;
	private Token br;
	
	public Token(){
		player = null;
	}
	
	public Token(Player player){
		this.player = player;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public void setTopCenter(Token token){
		tc = token;
	}
	
	public Token getTopCenter(){
		return tc;
	}
	
	public void setTopLeft(Token token){
		tl = token;
	}
	
	public Token getTopLeft(){
		return tl;
	}

	public Token getTopRight() {
		return tr;
	}

	public void setTopRight(Token tr) {
		this.tr = tr;
	}

	public Token getMiddleLeft() {
		return ml;
	}

	public void setMiddleLeft(Token ml) {
		this.ml = ml;
	}

	public Token getMiddleRight() {
		return mr;
	}

	public void setMiddleRight(Token mr) {
		this.mr = mr;
	}

	public Token getBottomLeft() {
		return bl;
	}

	public void setBottomLeft(Token bl) {
		this.bl = bl;
	}

	public Token getBottomCenter() {
		return bc;
	}

	public void setBottomCenter(Token bc) {
		this.bc = bc;
	}

	public Token getBottomRight() {
		return br;
	}

	public void setBottomRight(Token br) {
		this.br = br;
	}
	
	
}

