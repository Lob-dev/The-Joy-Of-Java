import {atom} from "recoil";

export const greetingState = atom<String>({
    key: 'greetingState',
    default: 'Say Something.'
})