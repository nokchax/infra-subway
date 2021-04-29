import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    vus: 1, // 1 user looping for 1 minute
    duration: '10s',

    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'http://localhost:8080';

export default () => {
    let inquireRes = http.get(`${BASE_URL}/paths?source=1&target=4`);

    check(inquireRes, {
        'inquire successfully': (resp) => resp.json('stations') !== undefined,
    });
    sleep(1);
};
