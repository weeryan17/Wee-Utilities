package tk.weeryan17.utilities.api.interfaces;

public interface SubCommand extends BaseCommand {
	/**
	 * Get's the sub args that where sent to the command.
	 * @return the sub args
	 */
	String[] subArgs();
	
	/**
	 * Returns a sub usage for working with when you have sub commands.
	 * Format works the same as the main getUsage
	 * 
	 * @return The sub usage
	 * @see BaseCommand#getUsage()
	 */
	default String getSubUsage() {
		return getUsage();
	}
	
	/**
	 * Makes it do you don't have to use {@link BaseCommand#getCommand()} 
	 */
	@Override
	default String getCommand(){
		return "";
	}
}
