import uvicorn
from fastapi import FastAPI

api: FastAPI = FastAPI()


@api.get("/greeting")
async def greeting() -> dict[str]:
    return dict(Say='greeting, I\'m Resource API!')


@api.get("/resources/{resource_id}")
async def greeting(resource_id: int) -> dict[str]:
    return dict(
        resource_id=resource_id,
        title="resource title",
        content="resource content",
        user_id="1"
    )


if __name__ == '__main__':
    uvicorn.run(app=api, port=9091, host="0.0.0.0")
