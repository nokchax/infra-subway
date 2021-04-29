import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        { duration: '1m', target: 400 },
        { duration: '1m', target: 800 },
        { duration: '1m', target: 1200 },
        { duration: '1m', target: 1600 },
        { duration: '1m', target: 2000 },
        { duration: '1m', target: 1600 },
        { duration: '1m', target: 1200 },
        { duration: '1m', target: 800 },
        { duration: '1m', target: 400 },
        { duration: '1m', target: 0 },
    ],
    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
        'inquire successfully': ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'https://nokchax.kro.kr';

export default () => {
    let inquireRes = http.get(`${BASE_URL}/paths?source=1&target=4`);

    check(inquireRes, {
        'inquire successfully': (resp) => resp.json('stations') !== undefined,
    });
    sleep(1);
};
