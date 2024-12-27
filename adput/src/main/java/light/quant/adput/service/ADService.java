package light.quant.adput.service;

import light.quant.adput.utils.ADLinkConstant;
import light.quant.adput.utils.ADResultUtil;
import light.quant.adput.utils.OkHttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author light
 * @Date 2024/12/8
 * @Desc
 **/
@Service
public class ADService {
    /**
     * 创建链接
     */
    public void createAD(String name, String videoIdStr) {
        try {
            System.out.println("创建投放链接开始");
            // 保存获取的结果
            Map<String, String> params = new HashMap<>();

            //1、获取videoID
            this.pubVideoId(name, videoIdStr, params);
            Thread.sleep(1000);

            //2、episodesID
            this.putEpisodesID(params);
            Thread.sleep(1000);

            // 3、创建投放链接
            this.putADLink(params);
            Thread.sleep(1000);

            // 4、创建落地页
            this.putShareLink(params);
            Thread.sleep(1000);

            // 获取落地页广告的标题和正文
            this.putShareInfo(params);

            // 5、复制广告
            this.copyAD(params);
            Thread.sleep(5000);

            // 6、更新广告
//            this.updateAD(params);
            System.out.println("创建投放链接结束");
        } catch (Exception e) {
            System.out.println("出错了" + e);
        }
    }

    private void updateAD(Map<String, String> params) {
        String adName = ADResultUtil.createLinkName(params.get(ADLinkConstant.VIDEO_ID),
                params.get(ADLinkConstant.LINK_ID),
                params.get(ADLinkConstant.LINK_NAME).split("/")[0]);
        OkHttpUtil.sendPOST(ADLinkConstant.UPDATE_DRAFT_URL,
                ADLinkConstant.getUpdateDraftParams(adName, params.get(ADLinkConstant.ONE_LEVEL_KEY), "1"), ADLinkConstant.getHeader());
        // 6、更新草稿：修改二级名称
        OkHttpUtil.sendPOST(ADLinkConstant.UPDATE_DRAFT_URL,
                ADLinkConstant.getUpdateDraftParams(adName, params.get(ADLinkConstant.TWO_LEVEL_KEY), "2"),
                ADLinkConstant.getHeader());

        // 7、更新三级数据
//            OkHttpUtils.sendPOST(ADLinkConstant.UPDATE_DRAFT_URL,
//                    ADLinkConstant.getUpdateDraftParamsLevelThree(threeOneLevelKey, shareLink), ADLinkConstant
//                    .getHeader());
//            Thread.sleep(1000);
    }


    private void copyAD(Map<String, String> params) {
        String result = OkHttpUtil.sendPOST(ADLinkConstant.COPY_AD_URL,
                ADLinkConstant.getCopyAdParams(), ADLinkConstant.getHeader());
        String oneLevelKey = ADResultUtil.getOneLevelKey(result);
        String twoLevelKey = ADResultUtil.getTwoLevelKey(result);
        System.out.println("oneLevelKey = " + oneLevelKey);
        System.out.println("twoLevelKey = " + twoLevelKey);
        params.put(ADLinkConstant.ONE_LEVEL_KEY, oneLevelKey);
        params.put(ADLinkConstant.TWO_LEVEL_KEY, twoLevelKey);
    }

    private void pubVideoId(String name, String videoIdStr, Map<String, String> params) {
        if (StringUtils.hasLength(name)) {
            String result = OkHttpUtil.sendPOST(ADLinkConstant.GET_VIDEO_ID_URL,
                    ADLinkConstant.getVideoIdParams(name), ADLinkConstant.getHeader());
            String videoId = ADResultUtil.getVideoId(result);
            System.out.println("videoId = " + videoId);
            params.put(ADLinkConstant.VIDEO_ID, videoId);
        } else {
            params.put(ADLinkConstant.VIDEO_ID, videoIdStr);
        }
    }

    private void putEpisodesID(Map<String, String> params) {
        String result = OkHttpUtil.sendPOST(ADLinkConstant.GET_EPISODES_ID_URL,
                ADLinkConstant.getEpisodesIdParams(params.get(ADLinkConstant.VIDEO_ID)), ADLinkConstant.getHeader());
        String episodesId = ADResultUtil.getEpisodesId(result);
        String episodesTitle = ADResultUtil.getEpisodesTitle(result);
        System.out.println("episodesId = " + episodesId);
        System.out.println("episodesTitle = " + episodesTitle);
        params.put(ADLinkConstant.EPISODES_ID, episodesId);
        params.put(ADLinkConstant.EPISODES_TITLE, episodesTitle);
    }

    private void putADLink(Map<String, String> params) {
        String result = OkHttpUtil.sendPOST(ADLinkConstant.CREATE_AD_LINK_URL,
                ADLinkConstant.getCreateAdLinkParams(params.get(ADLinkConstant.VIDEO_ID),
                        params.get(ADLinkConstant.EPISODES_ID)),
                ADLinkConstant.getHeader());
        String linkId = ADResultUtil.getLinkId(result);
        System.out.println("linkId = " + linkId);
        params.put(ADLinkConstant.LINK_ID, linkId);

        // 3、查询投放链接名称
        result = OkHttpUtil.sendPOST(ADLinkConstant.SEARCH_AD_LINK_URL,
                ADLinkConstant.getSearchTitleParams(linkId), ADLinkConstant.getHeader());
        String linkName = ADResultUtil.getLinkName(result);
        System.out.println("linkName = " + linkName);
        params.put(ADLinkConstant.LINK_NAME, linkName);
    }

    private void putShareLink(Map<String, String> params) {
        String result = OkHttpUtil.sendPOST(ADLinkConstant.CREATE_LINK_SHARE_URL,
                ADLinkConstant.getEditParams(params.get(ADLinkConstant.VIDEO_ID),
                        params.get(ADLinkConstant.EPISODES_ID), params.get(ADLinkConstant.EPISODES_TITLE)),
                ADLinkConstant.getHeader());
        String shareId = ADResultUtil.getShareId(result);
        System.out.println("shareId = " + shareId);
        params.put(ADLinkConstant.SHARE_ID, shareId);
        // 获取落地页链接
        result = OkHttpUtil.sendPOST(ADLinkConstant.LIST_LINK_SHARE_URL,
                ADLinkConstant.listShareInfo(), ADLinkConstant.getHeader());
        String shareLink = ADResultUtil.getShareLink(result, shareId);
        System.out.println("shareLink = " + shareLink);
        params.put(ADLinkConstant.SHARE_LINK, shareLink);
    }

    private void putShareInfo(Map<String, String> params){
//        String link = "https://website.kalostv.com/share/middle/sbh310gx1qiisaaqxxbe5d31";
        String shareLink = params.get(ADLinkConstant.SHARE_LINK);
        String result = OkHttpUtil.sendPOST(shareLink, "", new HashMap<>());
        String title = ADResultUtil.getShareTitle(result);
        String describe = ADResultUtil.getShareDescribe(result);
        System.out.println("shareTitle:" + title);
        System.out.println("describe:" + describe);
        params.put(ADLinkConstant.SHARE_TITLE, title);
        params.put(ADLinkConstant.SHARE_DESC, describe);
    }

}






















