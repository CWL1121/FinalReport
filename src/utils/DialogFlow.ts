import dialogflow from '@google-cloud/dialogflow';
require('dotenv').config();

export class DilogFlow {
    private CREDENTIALS: any;
    private PROJECID: String;
    private CONFIGURATION: { credentials: { private_key: any; client_email: any; }; };
    private sessionClient: any;

    constructor(){
        this.CREDENTIALS = JSON.parse(<string>process.env.test)
        this.PROJECID = this.CREDENTIALS.project_id
        this.CONFIGURATION = {
            credentials: {
                private_key: this.CREDENTIALS['private_key'],
                client_email: this.CREDENTIALS['client_email']
            }
        }
        this.sessionClient = new dialogflow.SessionsClient(this.CONFIGURATION);
    }

    detectIntent = async (queryText:string, sessionId:string) => {

        let sessionPath = this.sessionClient.projectAgentSessionPath(this.PROJECID, sessionId);
    
        let request = {
            session: sessionPath,
            queryInput: {
                text: {
                    text: queryText,
                    languageCode: "zh-CN",
                },
            },
        };
    
        const responses = await this.sessionClient.detectIntent(request);
        return {
            response: responses
        };
    }

    detectIntentAudio = async (audio:Buffer, sessionId:string) => {

        let sessionPath = this.sessionClient.projectAgentSessionPath(this.PROJECID, sessionId);
    
        // The text query request.
        const request = {
            session: sessionPath,
            queryInput: {
              audioConfig: {
                audioEncoding: 'AUDIO_ENCODING_LINEAR_16',
                languageCode: "zh-CN",
              },
            },
            inputAudio: audio,
          };
    
        const responses = await this.sessionClient.detectIntent(request);
    
        return {
            response: responses
        };
    }
}