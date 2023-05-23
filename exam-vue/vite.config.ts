import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

// @ts-ignore
import path from 'path'
import {createSvgIconsPlugin} from 'vite-plugin-svg-icons'

// https://vitejs.dev/config/
export default defineConfig({

    plugins: [
        vue(),
        createSvgIconsPlugin({
            // Specify the icon folder to be cached
            iconDirs: [path.resolve(process.cwd(), 'src/assets/icons')],
            // Specify symbolId format
            symbolId: 'icon-[dir]-[name]',
        })
    ],
    resolve: {
        alias: {
            '@': path.resolve('./src')
        }
    },
    css: {
        // scss全局变量的设置
        preprocessorOptions: {
            scss: {
                javascriptEnabled: true,
                additionalData: '@import "./src/styles/variable.scss";',
            },
        },
    },
    server: {
        proxy: {
            '/api': {
                target: 'http://localhost/api', // 目标 API 地址
                changeOrigin: true, // 是否改变请求源
                rewrite: (path) => path.replace(/^\/api/, '') // 重写请求路径，去掉 /api 前缀
            }
        }
    }
})
