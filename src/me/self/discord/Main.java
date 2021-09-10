package me.self.discord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main extends ListenerAdapter{

    // Command Listing and argument
    String[] nameArray = new String[]{"ping", "help"};
    List<String> commandList = new ArrayList<>(Arrays.asList(nameArray));

    //Prefix
    private static final String prefix = "!";
    public static void main(String[] args) throws LoginException
    {
        // Token
    final String token = args[0];
        JDA jda = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
            .addEventListeners(new Main())
            .build();

        jda.getPresence().setActivity(Activity.playing("Bot One At your Service"));
        jda.upsertCommand("ping", "content");
    }
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        // Print the aurthor an the message
        System.out.println("[" + msg.getAuthor().getName() + "]" + ": " + msg.getContentDisplay());

        // Checking and removing the prefix
        if (msg.getContentRaw().startsWith(prefix)){
            
            String command = msg.getContentRaw().substring(prefix.length()).toLowerCase();
            System.out.println(command);
            
            if(commandList.contains(command)){
                if (command.equals("ping")){
                    msg.reply("pong").queue();
                }
            }
            else{
                msg.reply("Command not found");
            }
        }
        else
            return;
    }
}
