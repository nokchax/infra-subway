import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        { duration: '1m', target: 500 }, // simulate ramp-up of traffic from 1 to 100 users over 5 minutes.
        { duration: '2m', target: 500 }, // stay at 100 users for 10 minutes
        { duration: '10s', target: 0 }, // ramp-down to 0 users
    ],
    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
        'logged in successfully': ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'http://localhost:8080';

export default () => {
    let joinRes = http.post(
        `${BASE_URL}/members`,
        JSON.stringify({
            email: 'nokchax@gmail.com',
            password: '1234',
            age: 10,
        }),
        { headers:{'Content-Type': 'application/json'} }
    );

    check(joinRes, {
        'joined successfully': (resp) => resp.status === 201,
    });

    sleep(1);
};
