package simulator.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import simulator.misc.Vector2D;
import simulator.model.BodiesGroup;
import simulator.model.Body;

@SuppressWarnings("serial")
class Viewer extends SimulationViewer {

	private static final int _WIDTH = 500;
	private static final int _HEIGHT = 500;

	// (_centerX,_centerY) is used as the origin when drawing
	// the bodies
	private int _centerX;
	private int _centerY;

	// values used to shift the actual origin (the middle of
	// the window), when calculating (_centerX,_centerY)
	private int _originX = 0;
	private int _originY = 0;

	// the scale factor, used to reduce the bodies coordinates
	// to the size of the component
	private double _scale = 1.0;

	// indicates if the help message should be shown
	private boolean _showHelp = true;

	// indicates if the position/velocity vectors should be shown
	private boolean _showVectors = true;

	// the list bodies and groups
	private List<Body> _bodies;
	private List<BodiesGroup> _groups;

	// a color generator, and a map that assigns colors to groups
	private ColorsGenerator _colorGen;
	private Map<String, Color> _gColor;

	// the index and Id of the selected group, -1 and null means all groups
	private int _selectedGroupIdx = -1;
	private String _selectedGroup = null;

	Viewer() {
		initGUI();
	}

	private void initGUI() {

		// add a border
		setBorder(BorderFactory.createLineBorder(Color.black, 2));

		// initialize the color generator, and the map, that we use
		// assign colors to groups
		_colorGen = new ColorsGenerator();
		_gColor = new HashMap<>();

		// initialize the lists of bodies and groups
		_bodies = new ArrayList<>();
		_groups = new ArrayList<>();

		// The preferred and minimum size of the components
		setMinimumSize(new Dimension(_WIDTH, _HEIGHT));
		setPreferredSize(new Dimension(_WIDTH, _HEIGHT));

		// add a key listener to handle the user actions
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
						
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			/*
			 * 
			 * TODO
			 * 
			 * EN: handle keys 'j','l','i','m' to add 10/-10 to _originX/_originY, and then
			 * call repaint(). This will make the origin point moves left/right/up/down. See
			 * how the values of _centerX and _centerY are calculated in method
			 * paintComponent
			 * 
			 * ES: Gestiona las teclas 'j','l','i','m' para sumar 10/-10 a
			 * _originX/_originY, y luego llame a repaint(). Esto har� que el punto de
			 * origen se mueva hacia la izquierda/derecha/arriba/abajo. Vea c�mo se calculan
			 * los valores de _centerX y _centerY en el m�todo paintComponent
			 */
			
			
			
			/*
			 * TODO
			 * 
			 * EN: handle key 'k' to set _originX and _originY to 0, and then call
			 * repaint(). This will return the origin point to the center of the window.
			 * 
			 * ES: Gestiona la tecla 'k' para poner _originX y _originY en 0, y luego llame
			 * a repaint(). Esto hace que el punto de origen sea el centro de la ventana.
			 * 
			 * TODO
			 * 
			 * EN: handle key 'h' to change the value of _showHelp to !_showHelp, and then
			 * call repaint(). This will make show/hide the help text - see method
			 * paintComponent
			 * 
			 * ES: gestiona la tecla 'h' para cambiar el valor de _showHelp a !_showHelp, y
			 * luego llame a repaint(). Esto har� que se muestre/oculte el texto de ayuda -
			 * ver m�todo paintComponent
			 * 
			 * TODO
			 * 
			 * EN: handle key 'v' to change the value of _showVectors to !_showVectors, and
			 * then call repaint(). You will use this variable in drawBodies to decide if to
			 * show or hide the velocity/force vectors.
			 * 
			 * ES: gestiona la tecla 'v' para cambiar el valor de _showVectors a
			 * !_showVectors, y luego llame a repaint(). Tienes que usar esta variable en
			 * drawBodies para decidir si mostrar u ocultar los vectores de
			 * velocidad/fuerza.
			 * 
			 * TODO
			 * 
			 * EN: handle key 'g' such that it makes the next group visible. Note that after
			 * the last group all bodies are shown again. This should be done by modifying
			 * _selectedGroupIdx from -1 (all groups) to _groups.size()-1 in a circular way.
			 * When its value is -1 you should set _selectedGroup to null, otherwise to the
			 * id of the corresponding group. Then in method showBodies you will draw only
			 * those that belong to the selected group.
			 * 
			 * ES: gestionar la tecla 'g' de manera que haga visible el siguiente grupo.
			 * Tenga en cuenta que despu�s del �ltimo grupo, se muestran todos los cuerpos.
			 * Esto se puede hacer modificando _selectedGroupIdx de -1 (todos los grupos) a
			 * _groups.size()-1 de forma circular. Cuando su valor es -1, _selectedGroup
			 * ser�a nulo, de lo contrario, ser�a el id del grupo correspondiente. En el
			 * m�todo showBodies, solo dibujar�s los que pertenecen al grupo seleccionado.
			 * 
			 */
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case '-':
					_scale = _scale * 1.1;
					repaint();
					break;
				case '+':
					_scale = Math.max(1000.0, _scale / 1.1);
					repaint();
					break;
				case '=':
					autoScale();
					repaint();
					break;

				case 'j':
					_originX += 10;
					repaint();
					break;
				case 'l':
					_originX -= 10;
					repaint();
					break;
				case 'i':
					_originY += 10;
					repaint();
					break;
				case 'm':
					_originY -= 10;
					repaint();
					break;
					
				case 'k':
					_originX = 0;
					_originY = 0;
					repaint();
					break;
					
				case 'h':
					_showHelp =!_showHelp;
					repaint();
					break;
				
				case 'v':
					_showVectors =!_showVectors;
					repaint();
					break;
					
				case 'g':
					if(_selectedGroupIdx == -1) {
						_selectedGroupIdx =_groups.size()-1;
						_selectedGroup = _groups.get(_selectedGroupIdx).getId();
					}
					else {
						_selectedGroupIdx--;
						if(_selectedGroupIdx != -1)
							_selectedGroup = _groups.get(_selectedGroupIdx).getId();
						else {
							_selectedGroup = null;
						}
					}
					repaint();
					break;
					
				default:
				}
			}
		});

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				requestFocus();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// a better graphics object
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// calculate the center
		_centerX = getWidth() / 2 - _originX;
		_centerY = getHeight() / 2 - _originY;

		// TODO draw red cross at (_centerX,_centerY)
		g.setColor(Color.RED);
		g.drawLine(_centerX, _centerY-5, _centerX, _centerY+5);
		g.drawLine(_centerX-5, _centerY, _centerX+5, _centerY);
		
		// draw bodies
		drawBodies(gr);

		// show help if needed
		if (_showHelp) {
			showHelp(gr);
		}
	}

	private void showHelp(Graphics2D g) {
		/*
		 * TODO
		 * 
		 * EN: complete to show the following text on the top-left corner:
		 * 
		 * h: toggle help, v: toggle vectors, +: zoom-in, -: zoom-out, =: fit //
		 * l: move right, j: move left, i: move up, m: move down: k: reset 
		 * g: show next group
		 * Scaling ratio: ... 
		 * Selected Group: ...
		 * 
		 * ES: completa el m�todo para que muestre el siguiente texto en la esquina
		 * superior izquierda:
		 * 
		 * h: toggle help, v: toggle vectors, +: zoom-in, -: zoom-out, =: fit 
		 * l: move right, j: move left, i: move up, m: move down: k: reset 
		 * g: show next group
		 * Scaling ratio: ... 
		 * Selected Group: ...
		 * 
		 */
		 
		g.setColor(Color.RED);
		g.drawString("h: toggle help, v: toggle vectors, +: zoom-in, -: zoom-out, =: fit", 10, 20);
		g.drawString("g: show next group", 10, 40);
		g.drawString("l: move right, j: move left, i: move up, m: move down: k: reset", 10, 60);
		g.drawString("Scaling ratio: " + _scale, 10, 80);
		g.setColor(Color.BLUE);
		if(_selectedGroupIdx != -1) {
			g.drawString("Selected Group: " + _selectedGroup, 10, 100);
		}
		else {
			g.drawString("Selected Group: all groups" , 10, 100);
		}

	
	}

	private void drawBodies(Graphics2D g) {
		/*
		 * TODO
		 * 
		 * EN: draw all bodies for which isVisible(b) return 'true' (see isVisible
		 * below, it returns true if the body belongs to the selected group). For each
		 * body, you should draw the velocity and force vectors if _showVectors is true.
		 * Use method drawLineWithArrow to draw the vectors. The color of body 'b'
		 * should be _gColor.get(b.getgId()) -- see method addGroup below. You should
		 * assume that the origin point is (_centerX,_centerY), and recall to divide the
		 * coordinates of the body by the value of _scale.
		 * 
		 * 
		 * ES: Dibuja todos los cuerpos para los que isVisible(b) devuelve 'true' (ver
		 * isVisible abajo, devuelve 'true' si el cuerpo pertenece al grupo
		 * seleccionado). Para cada cuerpo, debes dibujar los vectores de velocidad y
		 * fuerza si _showVectors es 'true'. Usa el m�todo drawLineWithArrow para
		 * dibujar los vectores. El color del cuerpo 'b' debe ser
		 * _gColor.get(b.getgId()) -- ver el m�todo addGroup. Como punto de origen usar
		 * (_centerX,_centerY), y recordar dividir las coordenadas del cuerpo por el
		 * valor de _scale.
		 * 
		 */
		Double vAux0;
		Double vAux1;
		Double fAux0;
		Double fAux1;
		for(Body b: _bodies) {
			if(isVisible(b)){
				Double c0 = (b.getPosition().getX()/_scale) + _centerX -5;
				Double c1 = (-(b.getPosition().getY())/_scale) + _centerY -5;
				Double c0b = c0 + 5;
				Double c1b = c1 + 5;
				
				if(_showVectors) {
					vAux0 = b.getVelocity().direction().getX()*30;
					int v0 = vAux0.intValue();
					vAux1 = -(b.getVelocity().direction().getY()*30);
					int v1 = vAux1.intValue();
					drawLineWithArrow(g, c0b.intValue(), c1b.intValue(), v0+c0b.intValue(), v1+c1b.intValue(), 3, 3, Color.GREEN, null);
					fAux0 = b.getForce().direction().getX()*30;
					int f0 = fAux0.intValue();
					fAux1 = -(b.getForce().direction().getY()*30);
					int f1 = fAux1.intValue();
					drawLineWithArrow(g, c0b.intValue(), c1b.intValue(), f0+c0b.intValue(), f1+c1b.intValue(), 3, 3, Color.RED, null);
				}
				g.setColor(_gColor.get(b.getgId()));
				g.fillOval(c0.intValue(), c1.intValue(), 10, 10);
				g.setColor(Color.BLACK);
				g.drawString(b.getId(),c0.intValue(), c1.intValue());
			}
		}
	}

	private boolean isVisible(Body b) {
		/*
		 * TODO 
		 * 
		 * EN: return true if _selectedGroup is null or equal to b.getgId() 
		 * 
		 * ES: devuelve true si _selectedGroup es null o igual a b.getgId()
		 *
		 */
		return (_selectedGroup == null || _selectedGroup.equals(b.getgId()));
	}

	// calculates a value for scale such that all visible bodies fit in the window
	private void autoScale() {

		double max = 1.0;

		for (Body b : _bodies) {
			Vector2D p = b.getPosition();
			max = Math.max(max, Math.abs(p.getX()));
			max = Math.max(max, Math.abs(p.getY()));
		}

		double size = Math.max(1.0, Math.min(getWidth(), getHeight()));

		_scale = max > size ? 4.0 * max / size : 1.0;
	}

	@Override
	public void addGroup(BodiesGroup g) {
		/*
		 * TODO
		 * 
		 * EN: add g to _groups and its bodies to _bodies
		 *
		 * ES: a�adir g a _groups y sus cuerpos a _bodies
		 * 
		 */
		_groups.add(g);
		for(Body b : g) {
			_bodies.add(b);
		}
		
		_gColor.put(g.getId(), _colorGen.nextColor()); // assign color to group
		autoScale();
		update();
	}

	@Override
	public void addBody(Body b) {
		/*
		 * TODO
		 * 
		 *  EN: add b to _bodies
		 *  
		 *  ES: a�adir b a _bodies
		 *  
		 */
		_bodies.add(b);
		
		autoScale();
		update();
	}

	@Override
	public void reset() {
		/*
		 * TODO
		 * 
		 * EN: clear the group list, bodies list, and the colors map
		 * 
		 * ES: borrar (usando el m�todo clear) la lista de grupos, la lista de cuerpos y
		 * el mapa de colores
		 * 
		 */
		_groups.clear();
		_bodies.clear();
		_gColor.clear();
		
		_colorGen.reset(); // reset the color generator
		_selectedGroupIdx = -1;
		_selectedGroup = null;
		update();
	}

	@Override
	void update() {
		repaint();
	}

	// This method draws a line from (x1,y1) to (x2,y2) with an arrow.
	// The arrow is of height h and width w.
	// The last two arguments are the colors of the arrow and the line
	private void drawLineWithArrow(//
			Graphics g, //
			int x1, int y1, //
			int x2, int y2, //
			int w, int h, //
			Color lineColor, Color arrowColor) {

		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - w, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;

		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;

		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;

		int[] xpoints = { x2, (int) xm, (int) xn };
		int[] ypoints = { y2, (int) ym, (int) yn };

		g.setColor(lineColor);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(arrowColor);
		g.fillPolygon(xpoints, ypoints, 3);
	}

}