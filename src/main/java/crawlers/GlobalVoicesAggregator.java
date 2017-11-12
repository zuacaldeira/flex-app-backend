/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import crawlers.globalVoices.GlobalVoicesCrawlerAM;
import crawlers.globalVoices.GlobalVoicesCrawlerAR;
import crawlers.globalVoices.GlobalVoicesCrawlerAYM;
import crawlers.globalVoices.GlobalVoicesCrawlerBG;
import crawlers.globalVoices.GlobalVoicesCrawlerBN;
import crawlers.globalVoices.GlobalVoicesCrawlerCA;
import crawlers.globalVoices.GlobalVoicesCrawlerCS;
import crawlers.globalVoices.GlobalVoicesCrawlerDA;
import crawlers.globalVoices.GlobalVoicesCrawlerDE;
import crawlers.globalVoices.GlobalVoicesCrawlerEL;
import crawlers.globalVoices.GlobalVoicesCrawlerEN;
import crawlers.globalVoices.GlobalVoicesCrawlerEO;
import crawlers.globalVoices.GlobalVoicesCrawlerES;
import crawlers.globalVoices.GlobalVoicesCrawlerFA;
import crawlers.globalVoices.GlobalVoicesCrawlerFIL;
import crawlers.globalVoices.GlobalVoicesCrawlerFR;
import crawlers.globalVoices.GlobalVoicesCrawlerHE;
import crawlers.globalVoices.GlobalVoicesCrawlerHI;
import crawlers.globalVoices.GlobalVoicesCrawlerHU;
import crawlers.globalVoices.GlobalVoicesCrawlerID;
import crawlers.globalVoices.GlobalVoicesCrawlerIT;
import crawlers.globalVoices.GlobalVoicesCrawlerJA;
import crawlers.globalVoices.GlobalVoicesCrawlerKM;
import crawlers.globalVoices.GlobalVoicesCrawlerKO;
import crawlers.globalVoices.GlobalVoicesCrawlerMG;
import crawlers.globalVoices.GlobalVoicesCrawlerMK;
import crawlers.globalVoices.GlobalVoicesCrawlerNE;
import crawlers.globalVoices.GlobalVoicesCrawlerOR;
import crawlers.globalVoices.GlobalVoicesCrawlerPA;
import crawlers.globalVoices.GlobalVoicesCrawlerPL;
import crawlers.globalVoices.GlobalVoicesCrawlerPS;
import crawlers.globalVoices.GlobalVoicesCrawlerPT;
import crawlers.globalVoices.GlobalVoicesCrawlerRO;
import crawlers.globalVoices.GlobalVoicesCrawlerRU;
import crawlers.globalVoices.GlobalVoicesCrawlerSQ;
import crawlers.globalVoices.GlobalVoicesCrawlerSR;
import crawlers.globalVoices.GlobalVoicesCrawlerSV;
import crawlers.globalVoices.GlobalVoicesCrawlerSW;
import crawlers.globalVoices.GlobalVoicesCrawlerTET;
import crawlers.globalVoices.GlobalVoicesCrawlerTR;
import crawlers.globalVoices.GlobalVoicesCrawlerUR;
import crawlers.globalVoices.GlobalVoicesCrawlerZHS;
import crawlers.globalVoices.GlobalVoicesCrawlerZHT;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author zua
 */
@Singleton
@Startup
@Lock(LockType.READ)
public class GlobalVoicesAggregator {

    @EJB
    private GlobalVoicesCrawlerAM globalVoicesCrawlerAM;
    @EJB
    private GlobalVoicesCrawlerAR globalVoicesCrawlerAR;
    @EJB
    private GlobalVoicesCrawlerAYM globalVoicesCrawlerAYM;
    @EJB
    private GlobalVoicesCrawlerBG globalVoicesCrawlerBG;
    @EJB
    private GlobalVoicesCrawlerBN globalVoicesCrawlerBN;
    @EJB
    private GlobalVoicesCrawlerCA globalVoicesCrawlerCA;
    @EJB
    private GlobalVoicesCrawlerCS globalVoicesCrawlerCS;
    @EJB
    private GlobalVoicesCrawlerDA globalVoicesCrawlerDA;
    @EJB
    private GlobalVoicesCrawlerDE globalVoicesCrawlerDE;
    @EJB
    private GlobalVoicesCrawlerEL globalVoicesCrawlerEL;
    @EJB
    private GlobalVoicesCrawlerEN globalVoicesCrawlerEN;
    @EJB
    private GlobalVoicesCrawlerEO globalVoicesCrawlerEO;
    @EJB
    private GlobalVoicesCrawlerES globalVoicesCrawlerES;
    @EJB
    private GlobalVoicesCrawlerFA globalVoicesCrawlerFA;
    @EJB
    private GlobalVoicesCrawlerFIL globalVoicesCrawlerFIL;
    @EJB
    private GlobalVoicesCrawlerFR globalVoicesCrawlerFR;
    @EJB
    private GlobalVoicesCrawlerHE globalVoicesCrawlerHE;
    @EJB
    private GlobalVoicesCrawlerHI globalVoicesCrawlerHI;
    @EJB
    private GlobalVoicesCrawlerHU globalVoicesCrawlerHU;
    @EJB
    private GlobalVoicesCrawlerID globalVoicesCrawlerID;
    @EJB
    private GlobalVoicesCrawlerIT globalVoicesCrawlerIT;
    @EJB
    private GlobalVoicesCrawlerJA globalVoicesCrawlerJA;
    @EJB
    private GlobalVoicesCrawlerKM globalVoicesCrawlerKM;
    @EJB
    private GlobalVoicesCrawlerKO globalVoicesCrawlerKO;
    @EJB
    private GlobalVoicesCrawlerMG globalVoicesCrawlerMG;
    @EJB
    private GlobalVoicesCrawlerMK globalVoicesCrawlerMK;
    @EJB
    private GlobalVoicesCrawlerNE globalVoicesCrawlerNE;
    @EJB
    private GlobalVoicesCrawlerOR globalVoicesCrawlerOR;
    @EJB
    private GlobalVoicesCrawlerPA globalVoicesCrawlerPA;
    @EJB
    private GlobalVoicesCrawlerPL globalVoicesCrawlerPL;
    @EJB
    private GlobalVoicesCrawlerPS globalVoicesCrawlerPS;
    @EJB
    private GlobalVoicesCrawlerPT globalVoicesCrawlerPT;
    @EJB
    private GlobalVoicesCrawlerRO globalVoicesCrawlerRO;
    @EJB
    private GlobalVoicesCrawlerRU globalVoicesCrawlerRU;
    @EJB
    private GlobalVoicesCrawlerSQ globalVoicesCrawlerSQ;
    @EJB
    private GlobalVoicesCrawlerSR globalVoicesCrawlerSR;
    @EJB
    private GlobalVoicesCrawlerSV globalVoicesCrawlerSV;
    @EJB
    private GlobalVoicesCrawlerSW globalVoicesCrawlerSW;
    @EJB
    private GlobalVoicesCrawlerTET globalVoicesCrawlerTET;
    @EJB
    private GlobalVoicesCrawlerTR globalVoicesCrawlerTR;
    @EJB
    private GlobalVoicesCrawlerUR globalVoicesCrawlerUR;
    @EJB
    private GlobalVoicesCrawlerZHS globalVoicesCrawlerZHS;
    @EJB
    private GlobalVoicesCrawlerZHT globalVoicesCrawlerZHT;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Schedule(hour = "*", minute = "10/30")
    public void aggregate1() {
        globalVoicesCrawlerAM.crawl();
        globalVoicesCrawlerAR.crawl();
        globalVoicesCrawlerAYM.crawl();
        globalVoicesCrawlerBG.crawl();
        globalVoicesCrawlerBN.crawl();
        globalVoicesCrawlerCA.crawl();
        globalVoicesCrawlerCS.crawl();
        globalVoicesCrawlerDA.crawl();
        globalVoicesCrawlerDE.crawl();
        globalVoicesCrawlerEL.crawl();
        globalVoicesCrawlerEN.crawl();
        globalVoicesCrawlerEO.crawl();
        globalVoicesCrawlerES.crawl();
    }




    @Schedule(hour = "*", minute = "13/30")
    public void aggregate2() {
        globalVoicesCrawlerFA.crawl();
        globalVoicesCrawlerFIL.crawl();
        globalVoicesCrawlerFR.crawl();
        globalVoicesCrawlerHE.crawl();
        globalVoicesCrawlerHI.crawl();
        globalVoicesCrawlerHU.crawl();
        globalVoicesCrawlerID.crawl();
        globalVoicesCrawlerIT.crawl();
        globalVoicesCrawlerJA.crawl();
        globalVoicesCrawlerKM.crawl();
        globalVoicesCrawlerKO.crawl();
        globalVoicesCrawlerMG.crawl();
        globalVoicesCrawlerMK.crawl();
        globalVoicesCrawlerNE.crawl();
        globalVoicesCrawlerOR.crawl();
    }
    
    @Schedule(hour = "*", minute = "16/30")
    public void aggregate3() {
        globalVoicesCrawlerPA.crawl();
        globalVoicesCrawlerPL.crawl();
        globalVoicesCrawlerPS.crawl();
        globalVoicesCrawlerPT.crawl();
        globalVoicesCrawlerRO.crawl();
        globalVoicesCrawlerRU.crawl();
        globalVoicesCrawlerSQ.crawl();
        globalVoicesCrawlerSR.crawl();
        globalVoicesCrawlerSV.crawl();
        globalVoicesCrawlerSW.crawl();
        globalVoicesCrawlerTET.crawl();
        globalVoicesCrawlerTR.crawl();
        globalVoicesCrawlerUR.crawl();
        globalVoicesCrawlerZHS.crawl();
        globalVoicesCrawlerZHT.crawl();
    }
}
