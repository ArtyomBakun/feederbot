package com.arba.feederbot.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.utils.DomainResolved;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import com.vk.api.sdk.queries.groups.GroupsGetByIdQuery;

import static com.arba.feederbot.config.Vk2TgFeederBotConfig.VK_ACCESS_TOKEN_KEY;
import static com.arba.feederbot.config.Vk2TgFeederBotConfig.getPropertyByKey;

public class VkPoller {

    private static final String VK_ACCESS_TOKEN = getPropertyByKey(VK_ACCESS_TOKEN_KEY);

    public String getKuAPost() {
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
//        UserAuthResponse authResponse = vk.oauth()
//                .userAuthorizationCodeFlow("7039339", CLIENT_SECRET, "http://example.com/callback", code)
//                .execute();
//
        UserActor actor = new UserActor(189269037, VK_ACCESS_TOKEN);
        DomainResolved kaiserundallah = null;
        try {
            kaiserundallah = vk.utils().resolveScreenName(actor, "kaiserundallah").execute();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        GroupsGetByIdQuery byIdQuery = vk.groups().getById(actor);
        GetResponse kualwall = null;
        try {
            kualwall = vk.wall().get(actor)
                    //                .ownerId(actor.getId())
                    .ownerId(-kaiserundallah.getObjectId())
                    .count(5)
//                    .filter(new WallGetFilter())
                    .offset(0)
                    .execute();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        String text = kualwall.getItems().get(4).getText();
        System.out.println(text);
        return text;
    }

}
