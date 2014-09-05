package enviromine.client.gui.menu;

import java.math.BigDecimal;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.client.config.GuiSlider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enviromine.client.gui.UI_Settings;

@SideOnly(Side.CLIENT)
public class EM_Gui_GuiSettings extends GuiScreen
{
	
	private GuiScreen parentGuiScreen;

	public EM_Gui_GuiSettings(GuiScreen par1GuiScreen)
	{
		this.parentGuiScreen = par1GuiScreen;
	}
	//id, x, y, width, height, text

	@Override
	public void initGui()
	{
		
		GuiSlider ScaleSlider = new GuiSlider(149, this.width / 2 + 2, this.height / 6 + 74 - 6, 150, 20, StatCollector.translateToLocal("options.enviromine.guiScaling")+ ": ", "x", .5F, 2F, UI_Settings.guiScale, true, true);

		ScaleSlider.precision = 2;
		ScaleSlider.updateSlider();
	
		GuiButton tempScalebutton = new GuiButton(151, this.width / 2 - 152, this.height / 6 + 30 - 6, 150, 20, StatCollector.translateToLocal("options.enviromine.tempScale")+": "+StatCollector.translateToLocal("empotions.tempScale.f"));

		GuiButton showstatusbutton = new GuiButton(156, this.width / 2 - 152, this.height / 6 + 96 - 6, 150, 20, StatCollector.translateToLocal("options.enviromine.status"));
		
		GuiButton showdebugbutton = new GuiButton(150, this.width / 2 - 152, this.height / 6 + 52 - 6, 150, 20, StatCollector.translateToLocal("options.enviromine.debug"));
		
		GuiButton showiconsbutton = new GuiButton(154,this.width / 2 - 152, this.height / 6 + 74 - 6 , 150, 20, StatCollector.translateToLocal("options.enviromine.showIcons"));

		GuiButton minbarsbutton = new GuiButton(158, this.width / 2 + 2, this.height / 6 + 52 - 6, 150, 20, StatCollector.translateToLocal("options.enviromine.minBars"));
	
		
		if(UI_Settings.minimalHud)
		{
			showstatusbutton.enabled = false;
			showiconsbutton.enabled = false;
		}
		else 
		{
			showstatusbutton.enabled = true;
			showiconsbutton.enabled = true;
		}		
		
		this.buttonList.add(ScaleSlider);

		tempScalebutton.displayString = I18n.format("options.enviromine.tempScale", new Object[0]) + ": " + I18n.format("options.enviromine.tempScale."+ (UI_Settings.useFarenheit == true ? "f" : "c" ), new Object[0]);
			this.buttonList.add(tempScalebutton);
		showstatusbutton.displayString =I18n.format("options.enviromine.status", new Object[0]) + ": " + I18n.format("options.enviromine.status."+ (UI_Settings.ShowText == true || UI_Settings.minimalHud == true ? "visible" : "hidden" ), new Object[0]);
			this.buttonList.add(showstatusbutton);
		showdebugbutton.displayString =I18n.format("options.enviromine.debug", new Object[0]) + ": " + I18n.format("options.enviromine.debug."+ (UI_Settings.ShowDebug == true ? "visible" : "hidden" ), new Object[0]);
			this.buttonList.add(showdebugbutton);
		showiconsbutton.displayString =I18n.format("options.enviromine.showIcons", new Object[0]) + ": " + I18n.format("options.enviromine.showIcons."+ (UI_Settings.ShowGuiIcons == true || UI_Settings.minimalHud == true ? "visible" : "hidden" ), new Object[0]);
			this.buttonList.add(showiconsbutton);
		minbarsbutton.displayString = I18n.format("options.enviromine.minBars", new Object[0]) + ": " + I18n.format("options.enviromine.minBars."+ (UI_Settings.minimalHud == true ? "on" : "off" ), new Object[0]);
			this.buttonList.add(minbarsbutton);
	
			this.buttonList.add(new GuiButton(157, this.width / 2 + 2, this.height / 6 + 30 - 6, 150, 20, StatCollector.translateToLocal("options.enviromine.barPos") +"..."));
			
			this.buttonList.add(new GuiButton(200, this.width / 2 -95, this.height / 6 + 168, I18n.format( "gui.back", new Object[0])));

	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return true;
	}
	
	/**
	 * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
	 */

	@Override
	public void actionPerformed(GuiButton par1GuiButton)
	{

		if(par1GuiButton.enabled)
		{
			switch(par1GuiButton.id)
			{
				case 200:
					this.mc.displayGuiScreen(parentGuiScreen);
					return;
				case 149:
					GuiSlider slider = (GuiSlider) par1GuiButton;
					
					BigDecimal num = new BigDecimal( slider.getValue());
					
					num = num.setScale(2, BigDecimal.ROUND_HALF_UP);
					
					UI_Settings.guiScale = num.floatValue();

					
					break;
				case 150:
					UI_Settings.ShowDebug = !UI_Settings.ShowDebug; 
						par1GuiButton.displayString =I18n.format("options.enviromine.debug", new Object[0]) + ": " + I18n.format("options.enviromine.debug."+ (UI_Settings.ShowDebug == true ? "visible" : "hidden" ), new Object[0]);
					break;
				case 151:
					UI_Settings.useFarenheit = !UI_Settings.useFarenheit;
						par1GuiButton.displayString = I18n.format("options.enviromine.tempScale", new Object[0]) + ": " + I18n.format("options.enviromine.tempScale."+ (UI_Settings.useFarenheit == true ? "f" : "c" ), new Object[0]);
					break;
				case 152:
					UI_Settings.insaneParticals = !UI_Settings.insaneParticals;
						par1GuiButton.displayString = I18n.format("options.enviromine.insaneParticles", new Object[0]) + ": " + I18n.format("options.enviromine.insaneParticles."+ (UI_Settings.insaneParticals == true ? "on" : "off" ), new Object[0]);
					break;
				case 154:
					UI_Settings.ShowGuiIcons = !UI_Settings.ShowGuiIcons;
						par1GuiButton.displayString =I18n.format("options.enviromine.showIcons", new Object[0]) + ": " + I18n.format("options.enviromine.showIcons."+ (UI_Settings.ShowGuiIcons == true ? "visible" : "hidden" ), new Object[0]);
					break;
				case 156:
					UI_Settings.ShowText = !UI_Settings.ShowText;
						par1GuiButton.displayString =I18n.format("options.enviromine.status", new Object[0]) + ": " + I18n.format("options.enviromine.status."+ (UI_Settings.ShowText == true ? "visible" : "hidden" ), new Object[0]);
					break;
				case 157:
					this.mc.displayGuiScreen(new EM_Gui_Bars(this));
					break;
				case 158:
					UI_Settings.minimalHud = !UI_Settings.minimalHud;
						par1GuiButton.displayString = I18n.format("options.enviromine.minBars", new Object[0]) + ": " + I18n.format("options.enviromine.minBars."+ (UI_Settings.minimalHud == true ? "on" : "off" ), new Object[0]);
					this.mc.displayGuiScreen(new EM_Gui_GuiSettings(this.parentGuiScreen));	
					break;
				case 159:
					UI_Settings.sweatParticals = !UI_Settings.sweatParticals;
						par1GuiButton.displayString = I18n.format("options.enviromine.sweatParticles", new Object[0]) + ": " + I18n.format("options.enviromine.sweatParticles."+ (UI_Settings.sweatParticals == true ? "on" : "off" ), new Object[0]);
					break;
			}
		}
	}

	//TODO Needs to be better.. and not called every tick
	@Override
	public void mouseClickMove(int p_146273_1_, int p_146273_2_, int lastbutton, long time) 
	{
		GuiSlider Slide = (GuiSlider) this.buttonList.get(0);
		if(Slide.dragging)
		{
			actionPerformed(Slide);	
		}
		

	}
	@Override
	public void onGuiClosed() 
	{
	    UI_Settings.saveSettings();	
	}
	
	
	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, StatCollector.translateToLocal("options.enviromine.guiSetting.title"), this.width / 2, 15, 16777215);
		super.drawScreen(par1, par2, par3);
	}
	
}
